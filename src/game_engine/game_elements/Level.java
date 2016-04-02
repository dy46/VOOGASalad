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
	
	private void initialize(){
		myWaves = new ArrayList<>();
	}
	
	/*
	* This API will allow the player to start a new wave. Returns true if next started, false if not.
	*/
	public boolean playNextWave(){
		if(!currentWave.isFinished()){
			return false;
		}
		Wave nextWave = getNextWave();
		if(nextWave != null){
			currentWave = nextWave;
			return true;
		}
		return false;
	}
	
	/*
	* Returns boolean value on whether or not the player has completed the current wave
	*/
	public boolean isFinished(){
		for(Wave wave : myWaves){
			if(!wave.isFinished()){
				return false;
			}
		}
		return true;
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
	
	private Wave getNextWave(){
		for(int x=0; x<myWaves.size(); x++){
			if(myWaves.get(x).equals(currentWave)){
				int nextWaveIndex = x+1;
				waveBoundsCheck(nextWaveIndex);
				return myWaves.get(nextWaveIndex);
			}
		}
		return null;
	}
	
	private void waveBoundsCheck(int index){
		if(index < 0 || index > myWaves.size()){
			throw new IndexOutOfBoundsException();
		}
	}

}
