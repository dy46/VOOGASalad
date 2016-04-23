package game_engine.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;

public class EncapsulationController {

	private GameEngineInterface myEngine;
	private EncapsulationChecker ec;

	public EncapsulationController (GameEngineInterface engine) {
		myEngine = engine;
		this.ec = new EncapsulationChecker();
	}

	public boolean encapsulates (Unit inner, Unit outer) {
		return ec.encapsulates(inner, outer);
	}

	public void resolveEncapsulations(List<Unit> terrains) {
		myEngine.getEnemies().forEach(t -> terrainHandling(t, terrains));
		myEngine.getTowers().forEach(t -> terrainHandling(t, terrains));
	}

	public List<Unit> getUnitsInRange(Bounds range){
		List<Unit> units = myEngine.getAllUnits();
		List<Unit> inRange = new ArrayList<>();
		for(Unit u : units){
			if(ec.encapsulatesBounds(u, range)){
				inRange.add(u);
			}
		}
		return inRange;
	}

	private void terrainHandling (Unit unit, List<Unit> terrains){
		for (int i = 0; i < terrains.size(); i++) {
			if ((!(unit == terrains.get(i)) && ec.encapsulates(CollisionDetector.getUseableBounds(unit.getProperties().getBounds(), 
			                                                                                   unit.getProperties().getPosition()), 
			                                                CollisionDetector.getUseableBounds(terrains.get(i).getProperties().getBounds(),
			                                                                                   terrains.get(i).getProperties().getPosition())))) {
				if (unit.isVisible()) {
					List<Affector> newAffectorsToApply =
							terrains.get(i).getAffectorsToApply().stream()
							.map(a -> a.copyAffector()).collect(Collectors.toList());
					unit.addAffectors(newAffectorsToApply);
				}
			}
		}
	}

}
