package game_engine.game_elements;
/*
* Internal API that is used in order to represent levels within a game. More specifically, 
* this API will be responsible for dealing with transitions in between waves of enemies, as well as 
* keeping track of the order in which waves occur, and the initial conditions for waves.
*/
public interface Level {
	/*
	* This API will allow the player to update all enemies and start new waves
	*/
	public void playNextWave();
	/*
	* Returns boolean value on whether or not the player has completed the current wave
	*/
	public boolean isWaveFinished();
	/*
	* Allows the level to set new waves for the level 
	*/
	public void addWave(Wave newWave);

}
