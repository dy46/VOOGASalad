package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.affectors.AffectorFactory;
import game_engine.affectors.AffectorLibrary;
import game_engine.factories.EnemyFactory;
import game_engine.factories.TowerFactory;
import game_engine.functions.FunctionFactory;
import game_engine.functions.FunctionLibrary;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;

/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * @author adamtache
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface{

	private int myTimer;
	private int nextWaveTimer;
	private int pause;
	private List<Level> myLevels;
	private List<Path> myPaths;
	
	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private CollisionDetector myCollider;
	private List<UnitProperties> myTowerTypes;
	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;

	public void setUpEngine(List<String> fileNames) {
		myLevels = new ArrayList<>();
		myPaths = new ArrayList<>();
	        Path p2 = new Path("Dirt");
                p2.addPosition(new Position(0,30));
                p2.addPosition(new Position(200, 30));
                p2.addPosition(new Position(200, 200));
                p2.addPosition(new Position(400, 200));
                p2.addPosition(new Position(400, 600));
	        myPaths.add(p2);
		myTowerTypes = new ArrayList<>();
		myIDFactory = new IDFactory();
		myProjectiles = new ArrayList<>();
		//projectiles must be intialized before towers
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myEnemys = new ArrayList<>();
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTowers = makeDummyTowers();
		myCollider = new CollisionDetector(this);
		myBalance = 0;
		myTimer = 0;
		nextWaveTimer = 0;
		myCurrentLevel = makeDummyLevel();
	}
	
	private List<Unit> makeDummyTowers() {
	    Position position2 = new Position(200, 300);
            Tower t = myTowerFactory.createFourWayTower("Tower", myProjectiles, position2);
            return new ArrayList<>(Arrays.asList(new Tower[]{t}));
	}

	private Level makeDummyLevel(){
		Wave w = new Wave("I'm not quite sure what goes here", 0);
		Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e2 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e3 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e4 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		e1.getProperties().setHealth(1);
		e2.getProperties().setHealth(1);
		e3.getProperties().setHealth(1);
		e4.getProperties().setHealth(1);
		w.addEnemy(e1, 0);
		w.addEnemy(e2, 60);
		w.addEnemy(e3, 60);
		w.addEnemy(e4, 60);
		
		Wave w2 = new Wave("I'm not quite sure what goes here", 240);
		Enemy e5 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e6 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e7 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e8 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		e5.getProperties().setHealth(1);
		e6.getProperties().setHealth(1);
		e7.getProperties().setHealth(1);
		e8.getProperties().setHealth(1);
		w2.addEnemy(e5, 0);
		w2.addEnemy(e6, 60);
		w2.addEnemy(e7, 60);
		w2.addEnemy(e8, 60);
		Level l = new Level("still not sure", w);
		l.addWave(w);
		l.addWave(w2);
		
		return l;
	}

	public List<String> saveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel(int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = 0;
	}

	public void playWave(int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves(){
		myCurrentLevel.playNextWave();
		pause = 0;
	}
	public void updateElements() { 
		nextWaveTimer++;
		if(pause < 2){
			myTimer++;
			myTowers.forEach(t -> t.update());
			myTowers.forEach(t -> ((Tower) t).fire());
			myEnemys.forEach(e -> e.update());
			
			
			myCollider.resolveEnemyCollisions(myProjectiles);
			Enemy newE = myCurrentLevel.update();
			if(newE != null){
				myEnemys.add(newE);
			}// tries to spawn new enemies using Waves
			if(myCurrentLevel.getCurrentWave().isFinished()){
				myProjectiles.forEach(t -> {
					t.setInvisible();
					t.setHasCollided(true);
				});
				pause++;
				nextWaveTimer = 0;
			}
			
			
		}
		else if(myCurrentLevel.getNextWave() != null && myCurrentLevel.getNextWave().getTimeBeforeWave() <= nextWaveTimer){
			continueWaves();
		}
		// below is true whenever level ends
		System.out.println(myCurrentLevel.getNextWave() == null && myCurrentLevel.getCurrentWave().isFinished());
		myProjectiles.forEach(p -> p.update());
	}

	public String getGameStatus() {
		return "Waves left: " + wavesLeft() + " Current wave: " + myCurrentLevel.toString();
	}

	public void addBalance(double money){
		myBalance += money;
	}

	public int wavesLeft(){
		return myCurrentLevel.wavesLeft();
	}

	public void addEnemy(Enemy enemy){
		myEnemys.add(enemy);
	}
	public void addLevel(Level level){
		myLevels.add(level);
	}
	public void addTower(String ID, int towerTypeIndex){
		//		towerTypeBoundsCheck(towerTypeIndex);
		//		UnitProperties towerProperties = myTowerTypes.get(towerTypeIndex);
		//		Tower newTower = new Tower(ID);
		//		newTower.upgrade(towerProperties);
		//		myTowers.add(newTower);
	}

	public void remove(Unit unit){
		String className = unit.getClass().getSimpleName();
		String instanceVarName = "my" + className + "s";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		List<Unit> listInstanceVar = null;
		try {
			listInstanceVar = (List<Unit>) f.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		listInstanceVar.remove(unit);
	}

	public void modifyTower(int activeTowerIndex, UnitProperties newProperties) {
		towerBoundsCheck(activeTowerIndex);
		myTowers.get(activeTowerIndex).setProperties(newProperties);
		myTowerTypes.set(activeTowerIndex, newProperties);
	}

	private void towerBoundsCheck(int index){
		if(index < 0 || index > myTowers.size()){
			throw new IndexOutOfBoundsException();
		}
	}

	private void towerTypeBoundsCheck(int index){
		if(index < 0 || index > myTowerTypes.size()){
			throw new IndexOutOfBoundsException();
		}
	}

	// Getters

	public Level getCurrentLevel(){
		return myCurrentLevel;
	}

	public IDFactory getIDFactory(){
		return myIDFactory;
	}

	public double getBalance(){
		return myBalance;
	}

	public List<Level> getLevels(){
		return myLevels;
	}
	
	public List<Path> getPaths(){
		return myPaths;
	}
	
	public List<Unit> getEnemies(){
		return myEnemys;
	}
	
	public List<Unit> getTowers() {
	    return myTowers;
	}

	public List<Unit> getProjectiles(){
		return myProjectiles;
	}

	public FunctionFactory getFunctionFactory(){
		return myFunctionFactory;
	}

	public FunctionLibrary getFunctionLibrary(){
		return myFunctionFactory.getFunctionLibrary();
	}

	public EnemyFactory getEnemyFactory() {
		return myEnemyFactory;
	}

	public AffectorLibrary getAffectorLibrary(){
		return myAffectorFactory.getAffectorLibrary();
	}

}