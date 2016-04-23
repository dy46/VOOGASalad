package game_engine.games;

import game_engine.GameEngineInterface;

public abstract class GameTimer extends Timer{

	private GameEngineInterface myWorkspace;

	public GameTimer(GameEngineInterface workspace){
		myWorkspace = workspace;
		reset();
	}
	
	public void setWorkspace(GameEngineInterface ws) throws Exception{
		this.myWorkspace = ws;
	}
	
	public GameEngineInterface getWS(){
		return myWorkspace;
	}
	
	public abstract void updateElements();
	
}