package auth_environment.backend;

import java.util.Collection;

import game_engine.game_elements.GameElement;
import javafx.scene.image.Image;

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
	
	// Get Names to display on Frontend 
	public Collection<String> getDisplayNames();
	
	// Gets display image for an Element. 
	public Image displayElement(String name); 
	
}
