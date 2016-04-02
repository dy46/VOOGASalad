package auth_environment.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class PeriodicTable {
	
	private Map<GameElement, List<GameElement>> myElementList = new HashMap <GameElement, List<GameElement>>();
	
	public PeriodicTable() {
		
	}

	// Adds a new element, should overwrite existing elements of the same name if they exist. 
//    public void addElement(GameElement element) {
//    	if myElementList.containsKey(element.getClass())
//    }

    public void display() {
    	
    }
    
}
