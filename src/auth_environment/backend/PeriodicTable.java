package auth_environment.backend;

import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class holds all created Game Elements. The Developer can click on elements
 * and place them on the map or add them to the game.
 *
 * Developer should be able to click on a Game Element. This copies the Game Element
 * to be placed on the MapDisplay.
 */

public interface PeriodicTable {

	// Adds a new element, should overwrite existing elements of the same name if they exist. 
    public void addElement(GameElement element); 

    public void display();
    
    

}
