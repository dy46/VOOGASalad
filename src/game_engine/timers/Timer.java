package game_engine.timers;

import java.util.List;

import game_engine.affectors.Affector;
import game_engine.games.IPlayerEngineInterface;
import game_engine.games.TD.TDGame;
import game_engine.timelines.Timeline;

/*
* External API interface that will be available to the authoring environment for extension and creation
* of XML files for use in games. API specifies some basic functionality of timers and which methods need to 
* be implemented for subclasses created in the authoring environment.
*/
public class Timer{
	
	private int myElapsedTicks;
	
	/*
	 * Progresses the program by one time step and updates all
	 * necessary properties dependent on time (position due to velocity,
	 * affector duration). 
	 */
	public void update(){
		myElapsedTicks++;
	}
	
	/*
	 * Returns the number of ticks since the program first executed.
	 */
	public int getTicks(){
		return myElapsedTicks;
	}
	
	public void reset(){
		myElapsedTicks = 0;
	}
	
}