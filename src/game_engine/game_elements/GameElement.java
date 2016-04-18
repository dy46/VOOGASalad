package game_engine.game_elements;

/**
 * This interface is the external API of the game element module for providing element information to the front-end.
 * @author adamtache
 *
 */

public abstract class GameElement {
	
	private String myName;
	
	public GameElement(String name){
		this.myName = name;
	}
	
	/*
	 * Returns a String representation of the element to display in PeriodicTable on front-end
	 * Allows game developer to choose from various game elements
	 */
	public String toString(){
		return myName;
	}
	
	
}