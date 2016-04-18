package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

public class Wave extends GameElement{

	private List<Enemy> myEnemies;
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
		for(Enemy e: myEnemies){
			if(e.isVisible()){
				numEnemies++;
			}
		}
		return numEnemies;
	}

	public boolean isFinished(){
		return getEnemiesLeft() == 0;
	}
	public void addEnemy(Enemy e, int spawnTime){
		myEnemies.add(e);
		mySpawnTimes.add(spawnTime);
	}
	/*
	 * Spawns an enemy at the spawn location of the level
	 */

	public Enemy tryToSpawnEnemy(){
		timeSinceLastSpawn++;
		if(myCurrentEnemy < myEnemies.size() && timeSinceLastSpawn >= mySpawnTimes.get(myCurrentEnemy)){
			Enemy enemy = myEnemies.get(myCurrentEnemy++);
			timeSinceLastSpawn = 0;
			return enemy;
		}
		return null;
	}

	public List<Enemy> getEnemies(){
		return myEnemies;
	}
}