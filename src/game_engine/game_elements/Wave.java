package game_engine.game_elements;

public interface Wave {
	
	/*
	 * Returns the number of enemies left in this wave
	 */
	public int getEnemiesLeft(); 
	
	/*
	 * Spawns an enemy at the spawn location of the level
	 */
	public void spawnEnemy();
}
