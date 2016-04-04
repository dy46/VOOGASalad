package auth_environment.backend;

import java.util.HashMap;
import java.util.Map;
import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This interface holds a Family of Game Elements (such as Enemy, Tower, etc.)
 *
 * Developer should be able to click on a Game Element. This copies the Game Element
 * to be placed on the MapDisplay.
 */

public class Family implements IAisle {
	
	private Map<String, GameElement> elementMap = new HashMap<String, GameElement>();
	
	public Family() {
		
	}

	// Check for "" (null) names in the Customization window. 
	@Override
	public void addElement(GameElement element) {
		if (!checkNameTaken(element.toString())) {
			elementMap.put(element.toString(), element);
		}
		else {
			this.handleDuplicateNames();
		}
	}
	
	// TODO: implement this method
	private void handleDuplicateNames() {
		System.out.println("Name already taken");
	}
	
	private boolean checkNameTaken(String name) {
		return elementMap.containsKey(name);
	}

	@Override
	public GameElement pick(String name) {
		return elementMap.get(name);
	}

}
