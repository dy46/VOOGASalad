package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Timer;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Wave;
import game_engine.properties.UnitProperties;

/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * @author adamtache
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface{

	private List<Level> myLevels;
	private List<Tower> myTowers;
	private List<Wave> myWaves;
	private List<Path> myPaths;
	private List<Enemy> myEnemies;
	private Timer myTimer;
	private List<UnitProperties> myTowerTypes;
	private Level myCurrentLevel;
	private String TIMER_ID = "0";

	public void setUpEngine(List<String> fileNames) {
		myTimer = new Timer(TIMER_ID);
		myLevels = new ArrayList<>();
		myTowers = new ArrayList<>();
		myWaves = new ArrayList<>();
		myPaths = new ArrayList<>();
		myEnemies = new ArrayList<>();
		myTowerTypes = new ArrayList<>();
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
		myLevels.forEach(l -> l.update());
		myTowers.forEach(t -> t.update());
		myWaves.forEach(w -> w.update());
		myPaths.forEach(p -> p.update());
		myEnemies.forEach(e -> e.update());
	}

	public String getGameStatus() {
		return "Wave left: " + wavesLeft() + " Current wave: " + myCurrentLevel.toString();
	}
	
	public int wavesLeft(){
		int numWavesLeft = 0;
		for(Wave wave : myWaves){
			if(!wave.isFinished()){
				numWavesLeft++;
			}
		}
		return numWavesLeft;
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
	
	public Timer getTimer(){
		return myTimer;
	}
	
	public Level getCurrentLevel(){
		return myCurrentLevel;
	}

}