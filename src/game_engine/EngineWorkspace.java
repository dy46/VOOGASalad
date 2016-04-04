package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Timer;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
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

	private List<Enemy> myEnemys;
	private List<Projectile> myProjectiles;

	private Timer myTimer;
	private CollisionDetector myCollider;
	private List<UnitProperties> myTowerTypes;
	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;
	
	public void setUpEngine(List<String> fileNames) {
		myLevels = new ArrayList<>();
		myTowers = new ArrayList<>();
		myPaths = new ArrayList<>();
		myEnemys = makeDummyEnemys();
		myTowerTypes = new ArrayList<>();
		myIDFactory = new IDFactory();
		myCollider = new CollisionDetector(this);
		myProjectiles = new ArrayList<>();
		initialize();
	}
	
	private List<Enemy> makeDummyEnemys() {
	    List<Enemy> enemies = new ArrayList<>();
	    Enemy e1 = new Enemy("Fire");
	    Health health = new Health(50);
	    Position position = new Position(200, 200);
	    Velocity velocity = new Velocity(0.5, 0);
	    List<Position> l1 = new ArrayList<>();
	    l1.add(new Position(0,0));
	    l1.add(new Position(62,0));
	    l1.add(new Position(62,62));
	    l1.add(new Position(0,62));
	    Bounds b = new Bounds(l1);
	    UnitProperties properties = new UnitProperties(health, null, null, velocity, b, position, null);
	    e1.setProperties(properties);
	    enemies.add(e1);
	    
	    Enemy e2 = new Enemy("Fire");
	    Health health2 = new Health(50);
	    Position position2 = new Position(500, 200);
	    Velocity velocity2 = new Velocity(-0.5, 0);
	    UnitProperties properties2 = new UnitProperties(health2, null, null, velocity2, b, position2, null);
	    e2.setProperties(properties2);
	    enemies.add(e2);
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
		myEnemys.forEach(e -> e.update());
		myCollider.resolveEnemyCollisions(myEnemys);

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

	public void addTower(String ID, int towerTypeIndex){
		towerTypeBoundsCheck(towerTypeIndex);
		UnitProperties towerProperties = myTowerTypes.get(towerTypeIndex);
		Tower newTower = new Tower(ID);
		newTower.upgrade(towerProperties);
		myTowers.add(newTower);
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
		return myEnemys;
	}

	public List<Projectile> getProjectiles(){
		return myProjectiles;
	}


}