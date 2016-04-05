package auth_environment.backend;

import java.util.HashMap;
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
 * 
 * This class gets passed to 1) Element Menu (for display) 2) Every single Customizer Window (to add/edit Elements)
 * 
 * Features:
 * 1) Developer should be able to click on a GameElement to edit its Properties
 * 2) Developer should be able to drag the GameElement onto the Map if it is place-able
 * 3) Adding a new GameElement or renaming an existing one should check for duplicate names. If so, the Developer
 * should be asked whether they want to overwrite the old one. 
 */

public class PeriodicTable implements ILibrary {
	
	// TODO: include other Game Elements. Let's test with just these two for now. 
	private Map<String, Family> myMap = new HashMap<String, Family>(); 
	
	public PeriodicTable() {
		
	}

	// Ask: should we use Reflection to add to the correct Family? 
	@Override
	public void addElement(GameElement element) {
		String elementType = element.getClass().getSimpleName();
		if (myMap.containsKey(elementType)) {
			myMap.get(elementType).addElement(element);
		}
		else {
			Family tempFamily = new Family(elementType); 
			tempFamily.addElement(element);
			myMap.put(elementType, tempFamily);
		}
	}

	@Override
	public GameElement pickElement(String elementType, String elementName) {
		return myMap.get(elementType).pick(elementName);
	}

	// TODO: keep in mind that we'll have to include Images 
	@Override
	public void display() {
	}
}
