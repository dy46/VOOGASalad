package game_engine.factories;

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
		Terrain ice = new Terrain("Ice");
		Affector speedUp = myAffectorLibrary.getAffector("ExpIncr", "PositionMove");
		ice.addAffector(speedUp);
		myTerrainLibrary.addTerrain(ice);
	}

	public TerrainLibrary getTerrainLibrary() {
		return myTerrainLibrary;
	}

}