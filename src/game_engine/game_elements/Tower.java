package game_engine.game_elements;

import game_engine.properties.UnitProperties;

/*
* External API interface that will be available to the authoring environment for extension and creation
* of XML files for use in games. API specifies some basic functionality of towers and which methods need to 
* be implemented for subclasses created in the authoring environment.
*/
public interface Tower{
	/*
	* method for activating the tower attack (subclasses implement different types of attack types).
	*/
	public void fire();
	/*
	* sells the tower, and removes it from the list of active towers
	*/
	public void sell();
	/*
	* changes the UnitProperties of the tower to reflect an upgrade (higher damage, better armor, etc).
	*/
	public void upgrade(UnitProperties newProps);

}
