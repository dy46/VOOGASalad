package game_engine.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.games.GameEngineInterface;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class EncapsulationDetector {

	private GameEngineInterface myEngine;

	public EncapsulationDetector (GameEngineInterface engine) {
		myEngine = engine;
	}

	public boolean encapsulates (Unit inner, Unit outer) {
		return encapsulates(inner.getProperties().getBounds().getPositions(),
				outer.getProperties().getBounds().getPositions());
	}

	public void resolveEncapsulations(List<Unit> terrains) {
		myEngine.getEnemies().forEach(t -> terrainHandling(t, terrains));
		myEngine.getTowers().forEach(t -> terrainHandling(t, terrains));
	}

	public List<Unit> getUnitsInRange(Bounds range){
		List<Unit> units = myEngine.getAllUnits();
		List<Unit> inRange = new ArrayList<>();
		for(Unit u : units){
			if(encapsulatesBounds(u, range)){
				inRange.add(u);
			}
		}
		return inRange;
	}

	public static boolean encapsulates(List<Position> inner, List<Position> outer) {
		for (Position pos : inner) {
			if (!insidePolygon(outer, pos)) {
				return false;
			}
		}
		return true;
	}

	private void terrainHandling (Unit unit, List<Unit> terrains){
		for (int i = 0; i < terrains.size(); i++) {
			if ((!(unit == terrains.get(i)) && encapsulates(unit, terrains.get(i)))) {
				if (unit.isVisible()) {
					List<Affector> newAffectorsToApply =
							terrains.get(i).getAffectorsToApply().stream()
							.map(a -> a.copyAffector()).collect(Collectors.toList());
					unit.addAffectors(newAffectorsToApply);
				}
			}
		}
	}

	private static boolean insidePolygon (List<Position> bounds, Position p) {
		int counter = 0;
		double xinters;
		Position p1 = bounds.get(0);
		int numPos = bounds.size();
		for (int i = 1; i <= numPos; i++) {
			Position p2 = bounds.get(i % numPos);
			if (p.getY() > Math.min(p1.getY(), p2.getY())) {
				if (p.getY() <= Math.max(p1.getY(), p2.getY())) {
					if (p.getX() <= Math.max(p1.getX(), p2.getX())) {
						if (p1.getY() != p2.getY()) {
							xinters =
									(p.getY() - p1.getY()) * (p2.getX() - p1.getX()) /
									(p2.getY() - p1.getY()) + p1.getX();
							if (p1.getX() == p2.getX() || p.getX() <= xinters)
								counter++;
						}
					}
				}
			}
			p1 = new Position(p2.getX(), p2.getY());
		}
		if (counter % 2 == 0)
			return (false);
		else
			return (true);
	}

	private boolean encapsulatesBounds(Unit inner, Bounds outer){
		return encapsulates(inner.getProperties().getBounds().getPositions(),
				outer.getPositions());
	}

}
