package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Timer;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;

/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * @author adamtache
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface{

	private List<Level> myLevels;
	private List<Tower> myTowers;
	private List<Path> myPaths;
	private List<Enemy> myEnemies;
	private Timer myTimer;
	private List<UnitProperties> myTowerTypes;
	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;
	
	public void setUpEngine(List<String> fileNames) {
		myLevels = new ArrayList<>();
		myTowers = new ArrayList<>();
		myPaths = new ArrayList<>();
		myEnemies = makeDummyEnemies();
		myTowerTypes = new ArrayList<>();
		myIDFactory = new IDFactory();
		initialize();
	}
	
	private List<Enemy> makeDummyEnemies() {
	    List<Enemy> enemies = new ArrayList<>();
	    Enemy e1 = new Enemy("Fire");
	    Health health = new Health(50);
	    Position position = new Position(200, 200);
	    Velocity velocity = new Velocity(0.1, 0);
	    UnitProperties properties = new UnitProperties(health, null, null, velocity, null, position, null);
	    e1.setProperties(properties);
	    enemies.add(e1);
	    return enemies;
	}
	
	private void initialize(){
		myTimer = null;
//		myTimer = new Timer(myIDFactory.createID(myTimer));
		myBalance = 0;
	}

	public List<String> saveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel(int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
	}

	public void playWave(int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void updateElements() {
//		myTowers.forEach(t -> t.update());
		myEnemies.forEach(e -> e.update());
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
		myEnemies.add(enemy);
	}

	public void addTower(String ID, int towerTypeIndex){
		towerTypeBoundsCheck(towerTypeIndex);
		UnitProperties towerProperties = myTowerTypes.get(towerTypeIndex);
		Tower newTower = new Tower(ID);
		newTower.upgrade(towerProperties);
		myTowers.add(newTower);
	}
	
	public void remove(Unit unit){
		String className = unit.getClass().getName();
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
	
	public Timer getTimer(){
		return myTimer;
	}
	
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
	
	public List<Tower> getTowers(){
		return myTowers;
	}
	
	public List<Path> getPaths(){
		return myPaths;
	}
	
	public List<Enemy> getEnemies(){
		return myEnemies;
	}

	
}