package game_engine.games;

/*
 * External API interface that will be available to the authoring environment for extension and creation
 * of XML files for use in games. API specifies some basic functionality of timers and which methods need to 
 * be implemented for subclasses created in the authoring environment.
 */
public class Timer{

	private int myElapsedTicks;
	private boolean paused;

	public Timer(){
		this.myElapsedTicks = 0;
	}

	/*
	 * Progresses the program by one time step and updates all
	 * necessary properties dependent on time (position due to velocity,
	 * affector duration). 
	 */
	public void update(){
		if(!paused)
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

	public void pause(){
		this.paused = true;
	}

	public void unpause(){
		this.paused = false;
	}
	
	public boolean isPaused(){
		return paused;
	}

}