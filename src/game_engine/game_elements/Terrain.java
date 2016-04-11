package game_engine.game_elements;

/*
* Internal API that will be used to reflect the inclusion of terrain effects in games. 
* Most implementations of the Terrain API will apply some sort of affector to game elements within
* a certain area on the map.
*/
public class Terrain extends CollidableUnit{
	
	public Terrain(String name) {
		super(name);
	}
	
}