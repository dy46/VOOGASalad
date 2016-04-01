package game_engine.game_elements;

/*
* External API interface that will be available to the authoring environment for extension and creation
* of XML files for use in games. API specifies some basic functionality of timers and which methods need to 
* be implemented for subclasses created in the authoring environment.
*/
public interface Timer {
	
	/*
	 * Progresses the program by one time step and updates all
	 * necessary properties dependent on time (position due to velocity,
	 * affector duration). 
	 */
	public void nextTimeStep();
	
	/*
	 * Returns the number of ticks since the program first executed.
	 */
	
	public int getTicks(); 
}
