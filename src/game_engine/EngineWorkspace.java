package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import game_data.GameData;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.game_elements.Wave;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface {

	private int nextWaveTimer;
	private boolean pause;
	private List<Level> myLevels;
	private List<Branch> myPaths;

	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private CollisionDetector myCollider;
	private List<Tower> myTowerTypes;
	private Level myCurrentLevel;
	//	private IDFactory myIDFactory;
	private double myBalance;
	private int myLives;

	private List<Unit> myTerrains;
	private List<Affector> myAffectors;

	public void setUpEngine (GameData gameData) {
		myLives = 3;
		myLevels = gameData.getLevels();
		myPaths = gameData.getPaths();
		System.out.println("My paths: " + myPaths);
		myEnemys = gameData.getEnemies();
		myProjectiles = gameData.getProjectiles();
		myTowers = gameData.getTowers();
		myTowerTypes = gameData.getTowerTypes();
		myAffectors = gameData.getAffectors();
		myTerrains = gameData.getTerrains();
		myCollider = new CollisionDetector(this);
		myBalance = 0;
		nextWaveTimer = 0;
		initialize();
	}

	private void initialize(){
		if(myLevels == null)	myLevels = new ArrayList<>();
		if(myPaths == null)		myPaths = new ArrayList<>();
		if(myEnemys == null)	myEnemys = new ArrayList<>();
		if(myProjectiles == null)	myProjectiles = new ArrayList<>();
		if(myTowers == null)	myTowers = new ArrayList<>();
		if(myTowerTypes == null)	myTowerTypes = new ArrayList<>();
		if(myTerrains == null)	myTerrains = new ArrayList<>();
		if(myAffectors == null)	myAffectors = new ArrayList<>();
		if(myLevels.size() == 0){
			Wave w = new Wave("temp", 0);
			Level l = new Level("temp2", w, 3);
			myLevels.add(l);
			myCurrentLevel = l;
		}
		myAffectors.stream().forEach(a -> a.setWorkspace(this));
		myCurrentLevel = myLevels.get(0);
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

}