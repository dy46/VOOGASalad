package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.TerrainLibrary;
import game_engine.properties.Movement;

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
		Affector speedUp = myAffectorLibrary.getAffector("PathFollow", "PositionMove");
		speedUp.setTTL(1);
		affectors.add(speedUp);
		Unit ice = new Unit("IceTerrain", new ArrayList<>(), 2);
		ice.addAffectorsToApply(affectors);
		myTerrainLibrary.addTerrain(ice);
		
		Unit ground = new Unit("GroundTerrain", new ArrayList<>(), 2);
		myTerrainLibrary.addTerrain(ground);

		//		List<Affector> affectors2 = new ArrayList<>();
		//		Affector expIncrDamage = myAffectorLibrary.getAffector("RandomPoison", "HealthDamage");
		//		expIncrDamage.setTTL(Integer.MAX_VALUE);
		//		affectors2.add(expIncrDamage);
		//		Unit poisonSpike = new Unit("PoisonSpikesTerrain", new ArrayList<>(), 2);
		//                poisonSpike.setTimelinesToApply(Arrays.asList(new AffectorTimeline(affectors2)));
		//		myTerrainLibrary.addTerrain(poisonSpike);

		List<Affector> affectors3 = new ArrayList<>();
		Affector constantDamage = myAffectorLibrary.getAffector("Constant", "HealthDamage");
		constantDamage.setTTL(1);
		affectors3.add(constantDamage);
		Unit spike = new Unit("SpikesTerrain", new ArrayList<>(), 2);
		spike.addAffectorsToApply(affectors3);
		myTerrainLibrary.addTerrain(spike);
	}

	public TerrainLibrary getTerrainLibrary() {
		return myTerrainLibrary;
	}

}