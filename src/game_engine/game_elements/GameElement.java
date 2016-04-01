package game_engine.game_elements;

import game_engine.properties.UnitProperties;

/**
 * This interface is the internal API for the game element module. It represents any game element, which include
 * both properties (represented by UnitProperties) and an ID.
 * @author adamtache
 *
 */

public interface GameElement {
	
	public abstract void update();
	
	public abstract String getID();
	
	public abstract UnitProperties getProperties();
	
	public abstract void setID(String ID);
	
	public abstract void setProperties(UnitProperties properties);
	
}