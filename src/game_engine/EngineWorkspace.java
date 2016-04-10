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
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;

/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * @author adamtache
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface{

	private int myTimer;
	private boolean pause;
	private List<Level> myLevels;
	private List<Path> myPaths;

	private List<Unit> myUnits;
	private List<Tower> myTowers;
	private List<Enemy> myEnemys;
	private List<Projectile> myProjectiles;

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
		myTowerTypes = new ArrayList<>();
		myIDFactory = new IDFactory();
		myProjectiles = new ArrayList<>();
		myUnits = new ArrayList<>();
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
		myCurrentLevel = makeDummyLevel();
		myUnits.addAll(myTowers);
		myUnits.addAll(myProjectiles);
		myUnits.addAll(myEnemys);
	}

	private List<Enemy> makeDummyEnemys() {
		Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		return new ArrayList<>(Arrays.asList(new Enemy[]{e1}));
	}
	private Level makeDummyLevel(){
		Wave w = new Wave("I'm not quite sure what goes here");
		Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e2 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e3 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		Enemy e4 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		w.addEnemy(e1, 0);
		w.addEnemy(e2, 60);
		w.addEnemy(e3, 60);
		w.addEnemy(e4, 60);
		Level l = new Level("still not sure", w);
		l.addWave(w);
		return l;
	}
	private List<Tower> makeDummyTowers() {
		Position position2 = new Position(200, 300);
		Tower t = myTowerFactory.createFourWayTower("Tower", myProjectiles, myUnits, position2);
		return new ArrayList<>(Arrays.asList(new Tower[]{t}));
	}

	public List<String> saveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel(int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = false;
	}

	public void playWave(int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void updateElements() { 
		if(!pause){
			myTimer++;
			myTowers.forEach(t -> t.update());
			myTowers.forEach(t -> t.fire());
			myEnemys.forEach(e -> e.update());
			myProjectiles.forEach(p -> p.update());
			
			myCollider.resolveEnemyCollisions(myProjectiles);
			// currently spawns enemies every 240 ticks
//			if(myTimer % 240 == 0 ) {
//				Enemy e = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
//				myEnemys.add(e);
//				myUnits.add(e);
//			}
			Enemy newE = myCurrentLevel.update();
			if(newE != null){
				System.out.println("hello");
				myEnemys.add(newE);
				myUnits.add(newE);
			}// tries to spawn new enemies using Waves
			if(myCurrentLevel.getCurrentWave().isFinished()){
				myProjectiles.clear();
				pause = true;
			}
			
		}
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

	public List<Enemy> getEnemies(){
		return myEnemys;
	}

	public List<Projectile> getProjectiles(){
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

	public List<Unit> getUnits() {
		return myUnits;
	}

}