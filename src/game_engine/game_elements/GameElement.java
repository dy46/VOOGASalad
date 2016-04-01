package game_engine.game_elements;

import java.util.List;

import game_engine.properties.UnitProperties;

/**
 * This interface is the internal API of the game element module. It represents any game element, which include
 * both properties (represented by UnitProperties) and an ID.
 * This interface will be used to run the current instance of a game.
 * @author adamtache
 *
 */

public interface GameElement {
	
	// allows engine to update elements by applying its affectors
	public abstract void update();
	
	// adds affector to unit's List of affectors
	public void addAffector(Affector affector);
	
	// provides unique ID of element
	public abstract String getID();
	
	// provides current UnitProperties of element
	public abstract UnitProperties getProperties();
	
	// allows settings of ID of element
	public abstract void setID(String ID);
	
	// allows setting of properties of elements
	public abstract void setProperties(UnitProperties properties);
	
	// provides currently list of element's Affectors
	public List<Affector> getAffectors();
	
	// allows settings of list of affectors for element
	public void setAffectors(List<Affector> affectors);
}