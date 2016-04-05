package auth_environment.backend;

import java.util.Collection;

import auth_environment.view.ElementCell;
import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This interface holds an Aisle of a Library of Game Elements (such as Enemy, Tower, etc.)
 *
 * Developer should be able to click on a Game Element. This copies the Game Element
 * to be placed on the MapDisplay.
 * 
 */

public interface IAisle {
	
	// Adds new Element to the Family. Checks for duplicate Names. 
	public void addElement(GameElement element);
	
	// Picks an Element with the given Name.  
	public GameElement pick(String name);
	
	// Return ElementCells to display
	public Collection<ElementCell> display();
	
	// Returns GameElement type as a String. 
	public String toString(); 
	
}
