package game_engine.game_elements;

import game_engine.EngineWorkspace;

/**
 * This interface is the external API of the game element module for providing element information to the front-end.
 * @author adamtache
 *
 */

public abstract class GameElement {
	
	private String myID;
	private EngineWorkspace myWorkspace;
	
	public GameElement(String ID){
		
	}
	/*
	 * Returns a String representation of the element to display in PeriodicTable on front-end
	 * Allows game developer to choose from various game elements
	 */	 
	public abstract String toString();
	
	/*
	 * Returns a String representation to be written to Game Data 
	 */
	public String toFile(){
		return myID;
	}
	
	public String getID(){
		return myID;
	}
	
	public EngineWorkspace getWorkspace(){
		return myWorkspace;
	}
	
}