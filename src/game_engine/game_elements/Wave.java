package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

public class Wave extends GameElement{

	private List<Unit> myEnemies;
	private List<Integer> mySpawnTimes;
	private int myCurrentEnemy;
	private int timeSinceLastSpawn;
	private int timeBeforeWave;

	public Wave(String name, int time){
		super(name);
//		setID(getWorkspace().getIDFactory().createID(this));
		timeBeforeWave = time;
		initialize();
	}

	private void initialize(){
		myEnemies = new ArrayList<>();
		mySpawnTimes = new ArrayList<>();
		myCurrentEnemy = 0;
		timeSinceLastSpawn = 0;
	}
	public int getTimeBeforeWave(){
		return timeBeforeWave;
	}
	/*
	 * Returns the number of enemies left in this wave
	 */
	public int getEnemiesLeft(){
		int numEnemies = 0;
		for(Unit e: myEnemies){
			if(e.isVisible()){
				numEnemies++;
			}
		}
		return numEnemies;
	}

	public boolean isFinished(){
		return getEnemiesLeft() == 0;
	}
	public void addEnemy(Unit e, int spawnTime){
		myEnemies.add(e);
		mySpawnTimes.add(spawnTime);
	}
	/*
	 * Spawns an enemy at the spawn location of the level
	 */

	public Unit tryToSpawnEnemy(){
		timeSinceLastSpawn++;
		if(myCurrentEnemy < myEnemies.size() && timeSinceLastSpawn >= mySpawnTimes.get(myCurrentEnemy)){
			Unit enemy = myEnemies.get(myCurrentEnemy++);
			timeSinceLastSpawn = 0;
			return enemy;
		}
		return null;
	}

	public List<Unit> getEnemies(){
		return myEnemies;
	}
}