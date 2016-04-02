package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

public class Wave extends Unit{

	private List<Enemy> myEnemies;

	public Wave(String ID){
		super(ID);
		initialize();
	}

	public void initialize(){
		myEnemies = new ArrayList<>();
	}

	/*
	 * Returns the number of enemies left in this wave
	 */
	public int getEnemiesLeft(){
		int numEnemies = 0;
		for(Enemy e: myEnemies){
			if(e.isAlive()){
				numEnemies++;
			}
		}
		return numEnemies;
	}
	
	/*
	 * Spawns an enemy at the spawn location of the level
	 */
	public void spawnEnemy(){
		
	}

	public String toFile() {
		return "Wave " + getID() + ", Number of enemies: " + getEnemiesLeft();
	}
	
}