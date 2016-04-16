package game_engine.properties;

import game_engine.games.TD.TDGame;

public abstract class Property {

	private TDGame myWorkspace;
	
	public void setWorkspace(TDGame workspace){
		this.myWorkspace = workspace;
	}
	
	public TDGame getWorkspace(){
		return myWorkspace;
	}
	
}