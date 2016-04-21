package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import game_data.GameData;
import game_engine.affectors.Affector;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.properties.UnitProperties;


/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 *
 */

// TODO: deprecate (Brian will handle) 

public class EngineWorkspace implements IPlayerEngineInterface {
	private List<Level> myLevels = new ArrayList<Level>();
	private List<Branch> myPaths = new ArrayList<Branch>();
	private List<Unit> myTowers = new ArrayList<Unit>();
	private List<Tower> myTowerTypes = new ArrayList<Tower>();
	private List<Unit> myProjectiles = new ArrayList<Unit>();
	private List<Unit> myEnemys = new ArrayList<Unit>();
	private List<Affector> myAffectors = new ArrayList<Affector>();
	private List<Unit> myTerrains = new ArrayList<Unit>();

	private CollisionDetector myCollider = new CollisionDetector(this);
	private Level myCurrentLevel;
	//	private IDFactory myIDFactory;
	private double myBalance;
	private int myLives;


	public void setUpEngine (GameData gameData) {
//		myLives = 3;
//		myLevels = gameData.getLevels();
//		myPaths = gameData.getPaths();
//		System.out.println("My paths: " + myPaths);
//		myEnemys = gameData.getEnemies();
//		myProjectiles = gameData.getProjectiles();
//		myTowers = gameData.getTowers();
//		myTowerTypes = gameData.getTowerTypes();
//		myAffectors = gameData.getAffectors();
//		myTerrains = gameData.getTerrains();
		myCollider = new CollisionDetector(this);
		myBalance = 0;
		nextWaveTimer = 0;
		initialize();
	}

	private void initialize(){
		myAffectors.stream().forEach(a -> a.setWorkspace(this));
		myCurrentLevel = myLevels.get(0);
		if(myLevels.size() == 0){
			Wave w = new Wave("temp", 0);
			Level l = new Level("temp2", w, 3);
			myLevels.add(l);
			myCurrentLevel = l;
		}
	}

	public void updateElements() {
		nextWaveTimer++;
		boolean gameOver = myLives <= 0;
		if(!pause && !gameOver){
			myTowers.forEach(t -> t.update());
			myTowers.forEach(t -> ((Tower) t).fire());
			myEnemys.forEach(e -> e.update());
			myCollider.resolveEnemyCollisions(myProjectiles, myTerrains);
			Enemy newE = myCurrentLevel.update();
			if(newE != null){
				myEnemys.add(newE);
			}// tries to spawn new enemies using Waves
			if(myCurrentLevel.getCurrentWave().isFinished()){
				clearProjectiles();
				pause = true;
				nextWaveTimer = 0;
			}       
		}
		else if(myCurrentLevel.getNextWave() != null && myCurrentLevel.getNextWave().getTimeBeforeWave() <= nextWaveTimer){
			continueWaves();
		}
		myProjectiles.forEach(p -> p.update());
		myTerrains.forEach(t -> t.update());
		updateLives();
	}

	public void updateLives () {
		int livesToSubtract = 0;
		for (int i = 0; i < myEnemys.size(); i++) {
			if (myEnemys.get(i).getProperties().getMovement().isUnitAtLastPosition(myEnemys.get(i))) {
				livesToSubtract++;
				myEnemys.get(i).setElapsedTimeToDeath();
			}
		}
		myCurrentLevel.setMyLives(myCurrentLevel.getStartingLives() - livesToSubtract);
	}

	public String getGameStatus () {
		return "Waves left: " + myCurrentLevel.wavesLeft() + " " + myCurrentLevel.toString() + " Number of Lifes: " + myCurrentLevel.getMyLives();
	}

	public void addBalance (double money) {
		myBalance += money;
	}

	public void addEnemy (Enemy enemy) {
		myEnemys.add(enemy);
	}

	public void addLevel (Level level) {
		myLevels.add(level);
	}

	public void remove (Unit unit) {
		String className = unit.getClass().getSimpleName();
		String instanceVarName = "my" + className + "s";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		}
		catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		List<Unit> listInstanceVar = null;
		try {
			listInstanceVar = (List<Unit>) f.get(this);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		listInstanceVar.remove(unit);
	}

	public void modifyTower (int activeTowerIndex, UnitProperties newProperties) {
		//		towerBoundsCheck(activeTowerIndex);
		//		myTowers.get(activeTowerIndex).setProperties(newProperties);
		//		myTowerTypes.set(activeTowerIndex, newProperties);
		//	
	}

	private void towerBoundsCheck (int index) {
		if (index < 0 || index > myTowers.size()) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void towerTypeBoundsCheck (int index) {
		if (index < 0 || index > myTowerTypes.size()) {
			throw new IndexOutOfBoundsException();
		}
	}

	public Level getCurrentLevel() { return myCurrentLevel; }

	public double getBalance() { return myBalance; }

	public List<Unit> getEnemies() { return myEnemys;	}

	public List<Unit> getTowers() { return myTowers; }

	public List<Level> getLevels() { return myLevels; }

	public List<Branch> getPaths() { return myPaths; }

	public List<Tower> getTowerTypes() { return myTowerTypes; }

	public List<Unit> getProjectiles() { return myProjectiles; }

	public List<Unit> getTerrains() { return myTerrains; }

	public int getLives () { return myCurrentLevel.getMyLives(); }

	public void clearProjectiles() {
		myProjectiles.forEach(t -> {
			t.setInvisible();
			t.setHasCollided(true);
		});
	}

	public List<String> saveGame () {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel (int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = false;
	}

	public void playWave (int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves () {
		myCurrentLevel.playNextWave();
		pause = false;
	}

	@Override
	public void addTower (String name, double x, double y) {
		for(int i = 0; i < myTowerTypes.size(); i++) {
			if(myTowerTypes.get(i).toString().equals(name)) {
				Tower newTower = myTowerTypes.get(i).copyTower(x, y);
				myTowers.add(newTower);
			}
		}
	}

	@Override
	public void setGameName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSplashScreen(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSplashScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Unit> getPlacedUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTower(Unit tower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTowers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTerrain(Unit terrain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTerrains() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEnemy(Unit enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearEnemies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProjectile(Unit projectile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAffector(Affector affector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Affector> getAffectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAffectors() {
		// TODO Auto-generated method stub
		
	}

}