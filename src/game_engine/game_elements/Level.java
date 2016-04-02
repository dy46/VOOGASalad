package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

/*
* Internal API that is used in order to represent levels within a game. More specifically, 
* this API will be responsible for dealing with transitions in between waves of enemies, as well as 
* keeping track of the order in which waves occur, and the initial conditions for waves.
*/
public class Level extends Unit{
	
	Wave currentWave;
	List<Wave> myWaves;
	
	public Level(String ID){
		super(ID);
		initialize();
	}
	
	public void initialize(){
		myWaves = new ArrayList<>();
	}
	
	/*
	* This API will allow the player to update all enemies and start new waves
	*/
	public void playNextWave(){
		
	}
	
	/*
	* Returns boolean value on whether or not the player has completed the current wave
	*/
	public boolean isWaveFinished(){
		return currentWave.getEnemiesLeft() == 0;
	}
	
	/*
	* Allows the level to set new waves for the level 
	*/
	public void addWave(Wave newWave){
		myWaves.add(newWave);
	}

	public String toFile() {
		return "Level " + getID() + ", Number of Waves: " + myWaves.size();
	}

}
