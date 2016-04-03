package auth_environment.backend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * 
 * Features:
 * 1) Developer should be able to click on a GameElement to edit its Properties
 * 2) Developer should be able to drag the GameElement onto the Map if it is place-able
 * 3) Adding a new GameElement or renaming an existing one should check for duplicate names. If so, the Developer
 * should be asked whether they want to overwrite the old one. 
 */

public class PeriodicTable {
	
	private Map<Class<?>, Set<GameElement>> myElementMap = new HashMap<Class<?>, Set<GameElement>>();
	
	public PeriodicTable() {
		
	}

	// Adds a new element, should overwrite existing elements of the same name if they exist. 
    public void addElement(GameElement element) {
    	if (!myElementMap.containsKey(element.getClass())) {
    	}
    } 

    public void display() {
    	
    }
    
}
