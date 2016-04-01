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
	/*
	* Updates the GameElement (changes position, applies affectors, etc.)
	*/
	public abstract void update();

	/*
	* returns a String identifier that can be used to connect backend and frontend components between
	* the game player and the game engine. (This could be used to allow a player to select units, for example).
	*/
	public abstract String getID();

	/*
	* Returns the UnitProperties object for use by other components of the game (mainly affectors)
	*/
	public abstract UnitProperties getProperties();
	
	/*
	* method sets the String identifier for the GameElement object. This can be used to connect
	* backend and frontend implementations of the game, so that users can select units on screen,
	* and the appropriate backend classes will be updated.
	*/
	public abstract void setID(String ID);
	
	/*
	* Initializes the properties of the GameElement 
	* according to the values proposed in the inputs 
	* to the method
	*
	* @param  properties  UnitProperties object that contains information that will be used to set properties
	*/
	public abstract void setProperties(UnitProperties properties);
	
	// provides currently list of element's Affectors
	public List<Affector> getAffectors();
	
	// allows settings of list of affectors for element
	public void setAffectors(List<Affector> affectors);
}