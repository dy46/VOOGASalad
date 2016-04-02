package game_engine.game_elements;

import game_engine.EngineWorkspace;

/**
 * This interface is the external API of the game element module for providing element information to the front-end.
 * @author adamtache
 *
 */

public abstract class GameElement {
	
	private String myID;
	private String myName;
	private EngineWorkspace myWorkspace;
	
	public GameElement(String name){
		this.myName = name;
	}
	
	public void setWorkspace(EngineWorkspace workspace){
		this.myWorkspace = workspace;
	}
	
	public String getID(){
		return myID;
	}
	
	public EngineWorkspace getWorkspace(){
		return myWorkspace;
	}
	
	public void setID(String ID){
		this.myID = ID;
	}
	
	/*
	 * Returns a String representation of the element to display in PeriodicTable on front-end
	 * Allows game developer to choose from various game elements
	 */
	public String toString(){
		return myName;
	}
	
}