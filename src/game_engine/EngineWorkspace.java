package game_engine;

import java.util.ArrayList;
import java.util.Collection;
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

	List <Level> myLevels;
	List<Tower> myTowers;
	List<Wave> myWaves;
	List<Path> myPaths;
	List<Enemy> myEnemies;
	Timer myTime;
	List<UnitProperties> myTowerTypes;

	public void setUpEngine(List<String> fileNames) {
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

	}

	public void playWave(int waveNumber) {
		// TODO Auto-generated method stub

	}

	public void updateElements() {

	}

	public List<Double> getGameStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTower(int towerTypeIndex) {
		towerTypeBoundsCheck(towerTypeIndex);
		UnitProperties towerProperties = myTowerTypes.get(towerTypeIndex);
		Tower newTower = new Tower();
		newTower.setProperties(towerProperties);
		myTowers.add(newTower);
	}

	public void modifyTower(int activeTowerIndex, UnitProperties newProperties) {s
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

}