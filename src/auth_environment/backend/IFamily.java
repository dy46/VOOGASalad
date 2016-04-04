package auth_environment.backend;

import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This interface holds a Family of Game Elements (such as Enemy, Tower, etc.)
 *
 * Developer should be able to click on a Game Element. This copies the Game Element
 * to be placed on the MapDisplay.
 * 
 */

public interface IFamily {
	
	// Adds new Element to the Family. Checks for duplicate Names. 
	public void addElement(GameElement element);
	
	// Re-order Elements by Name. 
	public void alphabetize();
	
	// Picks an Element at the given index. 
	public GameElement pick(int index);
	
}
