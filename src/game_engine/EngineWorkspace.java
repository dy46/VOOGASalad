package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;
import game_engine.functions.Function;
import game_engine.game_elements.CollidableUnit;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.libraries.TerrainLibrary;
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

	private List<Terrain> myTerrains;

	private CollisionDetector myCollider;
	private List<UnitProperties> myTowerTypes;
	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;
	private TerrainFactory myTerrainFactory;

	public void setUpEngine(List<String> fileNames) {
		myLevels = new ArrayList<>();
		myPaths = new ArrayList<>();
		myTowerTypes = new ArrayList<>();
		myIDFactory = new IDFactory();
		myProjectiles = new ArrayList<>();
		//projectiles must be intialized before towers
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myEnemys = makeDummyEnemys();
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTowers = makeDummyTowers();
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
		myTerrains = makeDummyTerrains();
		myCollider = new CollisionDetector(this);
		myBalance = 0;
	}

	private List<Terrain> makeDummyTerrains() {
		List<Terrain> terrains = new ArrayList<>();
		Terrain ice = myTerrainFactory.getTerrainLibrary().getTerrainByName("Ice");

		terrains.add(ice);
		return terrains;
	}

	private List<Enemy> makeDummyEnemys() {
		List<Enemy> enemies = new ArrayList<>();
		Enemy e1 = myEnemyFactory.createConstantEnemy("Fire");
		Health health = new Health(50);
		Position position = new Position(0, 200);
		Velocity velocity = new Velocity(0.5, 90);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(62,0));
		l1.add(new Position(62,62));
		l1.add(new Position(0,62));
		Bounds b = new Bounds(l1);
		UnitProperties properties = new UnitProperties(health, null, null, velocity, b, position, null);
		e1.setProperties(properties);
		e1.setTTL(Integer.MAX_VALUE);
		enemies.add(e1);
		return enemies;
	}

	private List<Tower> makeDummyTowers() {
		List<Tower> towers = new ArrayList<>();
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(62,0));
		l1.add(new Position(62,62));
		l1.add(new Position(0,62));
		Bounds b = new Bounds(l1);
		Health health2 = new Health(50);
		Position position2 = new Position(200, 300);
		Velocity velocity2 = new Velocity(0, 0);
		Tower t = myTowerFactory.createFourWayTower("Dude", myProjectiles, position2);
		UnitProperties properties2 = new UnitProperties(health2, null, null, velocity2, b, position2, null);
		t.setProperties(properties2);
		t.setTTL(Integer.MAX_VALUE);
		towers.add(t);
		return towers;
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
		myTowers.removeIf(t -> t.getTTL() == t.getElapsedTime());
		myTowers.forEach(t -> t.update());
		myTowers.forEach(t -> t.fire());
		myEnemys.removeIf(e -> e.getTTL() == e.getElapsedTime());
		myEnemys.forEach(e -> e.update());
		myProjectiles.removeIf(p -> p.getTTL() == p.getElapsedTime());
		myProjectiles.forEach(p -> p.update());
		myCollider.resolveEnemyCollisions(getCollideList());
		myCollider.resolveTowerCollisions(myTerrains);
	}
	
	private List<CollidableUnit> getCollideList(){
		List<CollidableUnit> collideList = new ArrayList<>();
		collideList.addAll(myTerrains);
		collideList.addAll(myProjectiles);
		return collideList;
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

	public List<Terrain> getTerrains() {
		return myTerrains;
	}

}