package game_engine.genres.TD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import game_data.GameData;
import game_engine.CollisionDetector;
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
import game_engine.games.GameEngineInterface;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.timelines.Timeline;
import game_engine.genres.TD.TDTimer;
import game_engine.games.Timer;


/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 *
 */

public class TDGame implements GameEngineInterface {

	private List<Level> myLevels;

	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private List<Tower> myTowerTypes;
	private Level myCurrentLevel;
	private double myBalance;

	private List<Unit> myTerrains;
	private List<Affector> myAffectors;

	private TDTimer myTimer;

	public void setUpEngine (GameData gameData) {
		myEnemys = gameData.getEnemies();
		myProjectiles = gameData.getProjectiles();
		myTowers = gameData.getTowers();
		myTowerTypes = gameData.getTowerTypes();
		myAffectors = gameData.getAffectors();
		myTerrains = gameData.getTerrains();
		myEnemys = gameData.getEnemies();
		myLevels = gameData.getLevels();
		myBalance = 0;
		initialize();
	}

	// TODO: make private after TestTDGame deleted
	public void initialize(){
		myTimer = new TDTimer(this);
		nullCheck();
		if(myLevels.size() == 0){
			System.out.println("My levels zero");
			Wave w = new Wave("temp", 0);
			Level l = new Level("temp2", 3);
			l.addWave(w);
			addLevel(l);
		}
		myCurrentLevel = myLevels.get(0);
		myCurrentLevel.setMyLives(3);

		setupAffectorWorkspaces();
	}
	
	public void setupAffectorWorkspaces(){
		myAffectors.stream().forEach(a -> a.setWorkspace(this));
		setEnemyAffectorWorkspaces();
	}
	
	public void setEnemyAffectorWorkspaces(){
		List<List<Timeline>> timelines = myEnemys.stream().map(e -> e.getTimelines()).collect(Collectors.toList());
		List<List<Wave>> waves = myLevels.stream().map(l -> l.getWaves()).collect(Collectors.toList());
		for(List<Wave> wList : waves){
			for(Wave w: wList){
				for(Enemy e : w.getEnemies()){
					for(Timeline t : e.getTimelines()){
						for(List<Affector> aList : t.getAffectors()){
							for(Affector a: aList){
								a.setWorkspace(this);
							}
						}
					}
				}
			}
		}
		for(List<Timeline> tList : timelines){
			for(Timeline t : tList){
				for(List<Affector> aList : t.getAffectors()){
					for(Affector a : aList){
						a.setWorkspace(this);
					}
				}
			}
		}
	}

	public void nullCheck(){
		if(myLevels == null){ myLevels = new ArrayList<>(); }
		if(myEnemys == null)	myEnemys = new ArrayList<>();
		if(myProjectiles == null)	myProjectiles = new ArrayList<>();
		if(myTowers == null)	myTowers = new ArrayList<>();
		if(myTowerTypes == null)	myTowerTypes = new ArrayList<>();
		if(myTerrains == null)	myTerrains = new ArrayList<>();
		if(myAffectors == null)	myAffectors = new ArrayList<>();
	}

	public String getGameStatus () {
		return "Waves left: " + myCurrentLevel.wavesLeft() + " " + myCurrentLevel.toString() + " Number of Lifes: " + myCurrentLevel.getMyLives();
	}

	public void addBalance (double money) {
		myBalance += money;
	}

	public void addEnemy (Enemy enemy) {
		if(enemy != null)
			myEnemys.add(enemy);
	}

	public void addLevel (Level level) {
		if(level == null)
			myLevels = new ArrayList<>();
		myLevels.add(level);
		if(myLevels.size() == 1)
			myCurrentLevel = level;
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
		myTimer.unpause();
	}

	public void playWave (int waveNumber) {
		//myCurrentLevel.getWaveTimer().pause();
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves () {
		System.out.println("CONTINUING WAVE");
		myCurrentLevel.playNextWave();
		myTimer.unpause();
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

	public boolean isPaused() {
		return myTimer.isPaused();
	}

	public void setPaused() {
		myTimer.pause();
	}

	public Wave getCurrentWave() {
		return myCurrentLevel.getCurrentWave();
	}

	public Timer getNextWaveTimer(){
		return myCurrentLevel.getWaveTimer();
	}

	public boolean isGameOver(){
		return myCurrentLevel.getMyLives() <= 0;
	}

	public Level getCurrentLevel() { return myCurrentLevel; }

	public double getBalance() { return myBalance; }

	public List<Unit> getEnemies() { return myEnemys;	}

	public List<Unit> getTowers() { return myTowers; }

	public List<Level> getLevels() { return myLevels; }

	public List<Tower> getTowerTypes() { return myTowerTypes; }

	public List<Unit> getProjectiles() { return myProjectiles; }

	public List<Unit> getTerrains() { return myTerrains; }

	public int getLives () { return myCurrentLevel.getMyLives(); }

	public void setTowerTypes(List<Tower> towers){ this.myTowerTypes = towers;	}

	public void setTerrains(List<Unit> terrains) {	this.myTerrains = terrains;	}

	public void setCurrentLevel(Level level) {	myCurrentLevel = level; }

	public void setAffectors(List<Affector> affectors) {	this.myAffectors = affectors; }

	public List<Affector> getAffectors(){	return myAffectors; }

	public Timer getTimer(){
		return myTimer;
	}

	public void update() {
		myTimer.update();
	}

	public void setupTimer(GameEngineInterface ws) {
		myTimer = new TDTimer((TDGame) ws);
	}
	
	public void decrementLives(){
		myCurrentLevel.decrementLife();
	}

}