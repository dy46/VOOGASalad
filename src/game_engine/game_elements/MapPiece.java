package game_engine.game_elements;

import game_engine.EngineWorkspace;

public abstract class MapPiece implements GameElement{

	private String myID;
	private EngineWorkspace myWorkspace;
	
	public MapPiece(String ID){
		this.myID = ID;
	}
	
	public EngineWorkspace getWorkspace(){
		return myWorkspace;
	}
	
	public String getID(){
		return myID;
	}
	
}