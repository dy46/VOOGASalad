package game_engine.timers;

import game_engine.games.IPlayerEngineInterface;

public abstract class GameTimer extends Timer{

	private IPlayerEngineInterface myWorkspace;

	public GameTimer(IPlayerEngineInterface workspace){
		myWorkspace = workspace;
		reset();
	}
	
	public void updateTimeStep(){
		super.update();
		updateElements();
	}
	
	public void setWorkspace(IPlayerEngineInterface ws) throws Exception{
		this.myWorkspace = ws;
	}
	
	public IPlayerEngineInterface getWS(){
		return myWorkspace;
	}
	
	public abstract void updateElements();
	
}