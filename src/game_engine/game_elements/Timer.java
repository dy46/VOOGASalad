package game_engine.game_elements;

/*
* External API interface that will be available to the authoring environment for extension and creation
* of XML files for use in games. API specifies some basic functionality of timers and which methods need to 
* be implemented for subclasses created in the authoring environment.
*/
public class Timer extends Unit{
	
	private int elapsedTicks;
	
	public Timer(String name){
		super(name);
		setID(getWorkspace().getIDFactory().createID(this));
		initialize();
	}
	
	private void initialize(){
		elapsedTicks = 0;
	}
	
	/*
	 * Progresses the program by one time step and updates all
	 * necessary properties dependent on time (position due to velocity,
	 * affector duration). 
	 */
	public void nextTimeStep(){
		getWorkspace().updateElements();
		elapsedTicks++;
	}
	
	/*
	 * Returns the number of ticks since the program first executed.
	 */
	public int getTicks(){
		return elapsedTicks;
	}

	public String toString() {
		return getID() + ", Elapsed time: " + elapsedTicks;
	}
	
}