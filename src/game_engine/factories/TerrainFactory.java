package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.TerrainBuildingBlock;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.TerrainLibrary;
import game_engine.properties.Movement;
import game_engine.timelines.Timeline;

public class TerrainFactory {

	private AffectorLibrary myAffectorLibrary;
	private TerrainLibrary myTerrainLibrary;

	public TerrainFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
		myTerrainLibrary = new TerrainLibrary();
		setupDefaultTerrains();
	}
	
	public Unit defineTerrainModel(BuildingBlock block){
		TerrainBuildingBlock terBlock = (TerrainBuildingBlock) block;
		List<Affector> affectors = new ArrayList<>();
		Affector speedUp = myAffectorLibrary.getAffector(terBlock.getMyProperty(), terBlock.getMyEffect());
		speedUp.setTTL(1);
		affectors.add(speedUp);
		Unit ter = new Unit(terBlock.getMyName(), new ArrayList<>(), 2);
	        ter.setTimelinesToApply(Arrays.asList(new AffectorTimeline(affectors)));
		myTerrainLibrary.addTerrain(ter);
		return ter;
	}
	
	private void setupDefaultTerrains(){
		List<Affector> affectors = new ArrayList<>();
		Affector speedUp = myAffectorLibrary.getAffector("PathFollow", "PositionMove");
		speedUp.setTTL(1);
		affectors.add(speedUp);
		Unit ice = new Unit("IceTerrain", new ArrayList<>(), 2);
	        ice.setTimelinesToApply(Arrays.asList(new AffectorTimeline(affectors)));
		myTerrainLibrary.addTerrain(ice);
		
		List<Affector> affectors2 = new ArrayList<>();
		Affector expIncrDamage = myAffectorLibrary.getAffector("RandomPoison", "HealthDamage");
		expIncrDamage.setTTL(Integer.MAX_VALUE);
		affectors2.add(expIncrDamage);
		Unit poisonSpike = new Unit("PoisonSpikesTerrain", new ArrayList<>(), 2);
                poisonSpike.setTimelinesToApply(Arrays.asList(new AffectorTimeline(affectors2)));
		myTerrainLibrary.addTerrain(poisonSpike);
		
		List<Affector> affectors3 = new ArrayList<>();
		Affector constantDamage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		constantDamage.setTTL(1);
		affectors3.add(constantDamage);
		Unit spike = new Unit("SpikesTerrain", new ArrayList<>(), 2);
		spike.setTimelinesToApply(Arrays.asList(new AffectorTimeline(affectors3)));
		myTerrainLibrary.addTerrain(spike);
	}

	public TerrainLibrary getTerrainLibrary() {
		return myTerrainLibrary;
	}

}