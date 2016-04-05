package auth_environment.backend;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import auth_environment.view.ElementCell;
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
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private Map<String, GameElement> elementMap = new HashMap<String, GameElement>();
	private String name; 
	
	public Family(String name) {
		this.name = name;
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
		System.out.println(myNamesBundle.getString("nameTakenError"));
	}
	
	private boolean checkNameTaken(String name) {
		return elementMap.containsKey(name);
	}

	// TODO: test
	@Override
	public GameElement pick(String name) {
		return elementMap.get(name);
	}

	@Override
	public Collection<ElementCell> display() {
		return elementMap.values().stream().map(s -> new ElementCell(s)).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
