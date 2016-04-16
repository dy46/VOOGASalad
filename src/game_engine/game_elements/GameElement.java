package game_engine.game_elements;

import game_engine.genres.TD.TDGame;

/**
 * This interface is the external API of the game element module for providing element information to the front-end.
 * @author adamtache
 *
 */

public abstract class GameElement {
	
	private String myName;
	private TDGame myWorkspace;
	
	public GameElement(String name){
		this.myName = name;
	}
	
	public void setWorkspace(TDGame workspace){
		this.myWorkspace = workspace;
	}
	
	public TDGame getWorkspace(){
		return myWorkspace;
	}
	
	/*
	 * Returns a String representation of the element to display in PeriodicTable on front-end
	 * Allows game developer to choose from various game elements
	 */
	public String toString(){
		return myName;
	}
	
	
}