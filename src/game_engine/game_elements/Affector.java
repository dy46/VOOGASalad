package game_engine.game_elements;

import game_engine.properties.UnitProperties;

public interface Affector {
	/*
	* Applies an effect to a unit by altering the 
	* UnitProperties of a GameElement object. The effect is determined
	* by the implementation of the method (this could involve)
	* decrementing health, increasing/decreasing speed, etc.
	* The overall effect is dependent on which properties are changed
	*
	* @param  properties  A UnitProperties object that represents the current state of the GameElement 
	*
	*
	*/
	public abstract void apply(UnitProperties properties);
	
}