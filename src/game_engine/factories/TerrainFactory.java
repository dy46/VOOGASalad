package game_engine.factories;

import java.util.ArrayList;
import java.util.List;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.TerrainBuildingBlock;
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
	
	public Terrain defineTerrainModel(BuildingBlock block){
		TerrainBuildingBlock terBlock = (TerrainBuildingBlock) block;
		List<Affector> affectors = new ArrayList<>();
		Affector speedUp = myAffectorLibrary.getAffector(terBlock.getMyProperty(), terBlock.getMyEffect());
		speedUp.setTTL(1);
		affectors.add(speedUp);
		Terrain ter = new Terrain(terBlock.getMyName(), 2);
		ter.setAffectorsToApply(affectors);
		myTerrainLibrary.addTerrain(ter);
		return ter;
	}
	
	private void setupDefaultTerrains(){
		List<Affector> affectors = new ArrayList<>();
		Affector speedUp = myAffectorLibrary.getAffector("PathFollow", "PositionMove");
		speedUp.setTTL(1);
		affectors.add(speedUp);
		Terrain ice = new Terrain("Ice", 2);
		ice.setAffectorsToApply(affectors);
		myTerrainLibrary.addTerrain(ice);
		
		List<Affector> affectors2 = new ArrayList<>();
		Affector expIncrDamage = myAffectorLibrary.getAffector("RandomPoison", "HealthDamage");
		expIncrDamage.setTTL(Integer.MAX_VALUE);
		affectors2.add(expIncrDamage);
		Terrain poisonSpike = new Terrain("PoisonSpikes", 2);
		poisonSpike.setAffectorsToApply(affectors2);
		myTerrainLibrary.addTerrain(poisonSpike);
		
		List<Affector> affectors3 = new ArrayList<>();
		Affector constantDamage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		constantDamage.setTTL(3);
		affectors3.add(constantDamage);
		Terrain spike = new Terrain("Spikes", 2);
		spike.setAffectorsToApply(affectors3);
		myTerrainLibrary.addTerrain(spike);
	}

	public TerrainLibrary getTerrainLibrary() {
		return myTerrainLibrary;
	}

}