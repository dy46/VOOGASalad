package game_engine.properties;

import game_engine.EngineWorkspace;

public abstract class Property {

	private EngineWorkspace myWorkspace;
	
	public void setWorkspace(EngineWorkspace workspace){
		this.myWorkspace = workspace;
	}
	
	public EngineWorkspace getWorkspace(){
		return myWorkspace;
	}
	
}