package game_engine.factories;

import java.util.ArrayList;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Terrain;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.TerrainLibrary;

public class TerrainFactory {

	private AffectorLibrary myAffectorLibrary;
	private TerrainLibrary myTerrainLibrary;

	public TerrainFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
		myTerrainLibrary = new TerrainLibrary();
		setupDefaultTerrains();
	}
	
	private void setupDefaultTerrains(){
		List<Affector> affectors = new ArrayList<>();
		Affector speedUp = myAffectorLibrary.getAffector("ExpIncr", "PositionMove");
		affectors.add(speedUp);
		Terrain ice = new Terrain("Ice", affectors);
		myTerrainLibrary.addTerrain(ice);
	}

	public TerrainLibrary getTerrainLibrary() {
		return myTerrainLibrary;
	}

}