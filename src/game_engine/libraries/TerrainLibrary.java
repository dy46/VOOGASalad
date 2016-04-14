package game_engine.libraries;

import java.util.HashMap;
import game_engine.game_elements.Terrain;

public class TerrainLibrary {

	private HashMap<String, Terrain> myTerrains;
	
	public TerrainLibrary() {
		myTerrains = new HashMap<>();
	}
	
	public Terrain getTerrainByName(String name) {
		return myTerrains.get(name).copyTerrain();
	}
	
	public void addTerrain(Terrain terrain){
		myTerrains.put(terrain.toString(), terrain);
	}

}