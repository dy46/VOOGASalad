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

/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * @author adamtache
 *
 */

public class EngineWorkspace implements IPlayerEngineInterface{

	Collection <Level> myLevels;
	Collection<Tower> myTowers;
	Collection<Wave> myWaves;
	Collection<Path> myPaths;
	Collection<Enemy> myEnemies;
	Timer myTime;
	
	public void setUpEngine(List<String> fileNames) {
		myLevels = new ArrayList<>();
		myTowers = new ArrayList<>();
		myWaves = new ArrayList<>();
		myPaths = new ArrayList<>();
		myEnemies = new ArrayList<>();
	}
	
	public List<String> saveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel(int levelNumber) {
		// TODO Auto-generated method stub
		
	}

	public void playWave(int waveNumber) {
		// TODO Auto-generated method stub
		
	}

	public void updateElements() {
		// TODO Auto-generated method stub
		
	}

	public List<Double> getGameStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTower(int towerIndex) {
		// TODO Auto-generated method stub
		
	}

	public void modifyTower(int activeTowerIndex, List<Double> changes) {
		// TODO Auto-generated method stub
		
	}

}
