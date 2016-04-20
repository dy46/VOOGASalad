package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.game_elements.Unit;
import game_engine.games.GameEngineInterface;
import game_engine.properties.Bounds;
import game_engine.properties.Position;


public class CollisionDetector {

	private GameEngineInterface myEngine;

	public CollisionDetector (GameEngineInterface engine) {
		myEngine = engine;
	}

	public void resolveEnemyCollisions (List<Unit> myProjectiles, List<Unit> myTerrains) {
		myEngine.getEnemies().forEach(t -> updateEnemies(t, myProjectiles));
		myEngine.getEnemies().forEach(t -> terrainHandling(t, myTerrains));
	}

	public void resolveTowerCollisions (List<Unit> terrains) {
		myEngine.getTowers().forEach(t -> terrainHandling(t, terrains));
	}

	// returns which Unit from the list collided with the target unit
	private void updateEnemies (Unit unit, List<Unit> myProjectiles) {
		for (int i = 0; i < myProjectiles.size(); i++) {
			if (!(unit == myProjectiles.get(i)) && collides(unit, myProjectiles.get(i))) {
				if (!myProjectiles.get(i).hasCollided() && unit.isVisible()) {
					List<AffectorTimeline> affectorsToApply = myProjectiles.get(i).getTimelinesToApply()
							.stream().map(p -> p.copyTimeline()).collect(Collectors.toList());                     
					unit.addTimelines(affectorsToApply);
					myProjectiles.get(i).setHasCollided(true);        
					myProjectiles.get(i).setElapsedTimeToDeath();
				}
			}
		}
	}

	private void terrainHandling (Unit unit, List<Unit> terrains) {
		for (int i = 0; i < terrains.size(); i++) {
			if (!(unit == terrains.get(i)) && collides(unit, terrains.get(i)) ||
					(!(unit == terrains.get(i)) && encapsulates(unit, terrains.get(i)))) {
				if (!terrains.get(i).hasCollided() && unit.isVisible()) {
					List<AffectorTimeline> newTimelinesToApply =
							terrains.get(i).getTimelinesToApply().stream()
							.map(t -> t.copyTimeline()).collect(Collectors.toList());
					unit.addTimelines(newTimelinesToApply);
				}
			}
		}
	}

	public static List<Position> getUseableBounds (Bounds bounds, Position pos) {
		List<Position> newBounds = new ArrayList<Position>();
		for (Position p : bounds.getPositions()) {
			Position newP = new Position(p.getX() + pos.getX(), p.getY() + pos.getY());
			newBounds.add(newP);
		}
		return newBounds;
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

	public static boolean encapsulates(List<Position> inner, List<Position> outer) {
		for (Position pos : inner) {
			if (!insidePolygon(outer, pos)) {
				return false;
			}
		}
		return true;
	}

	private boolean encapsulates (Unit inner, Unit outer) {
		return encapsulates(inner.getProperties().getBounds().getPositions(),
				outer.getProperties().getBounds().getPositions());
	}

	private boolean collides (Unit a, Unit b) {
		List<Position> aPos = getUseableBounds(a.getProperties().getBounds(),
				a.getProperties().getPosition());
		List<Position> bPos = getUseableBounds(b.getProperties().getBounds(),
				b.getProperties().getPosition());
		for (int i = 0; i < aPos.size(); i++) {
			for (int j = 0; j < bPos.size(); j++) {
				if (intersect(aPos.get(i), aPos.get((i + 1) % aPos.size()), bPos.get(j),
						bPos.get((j + 1) % bPos.size()))) {
					return true;
				}
			}
		}
		return false;
	}

	// check if q is on segment defined by p and r
	private boolean segmentOverlap (Position p, Position q, Position r) {
		return (q.getX() <= Math.max(p.getX(), r.getX()) &&
				q.getX() >= Math.min(p.getX(), r.getX()) &&
				q.getY() <= Math.max(p.getY(), r.getY()) &&
				q.getY() >= Math.min(p.getY(), r.getY()));
	}

	private boolean intersect (Position p1, Position q1, Position p2, Position q2) {
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
		boolean special =
				(o1 == 0 && segmentOverlap(p1, p2, q1)) ||
				(o2 == 0 && segmentOverlap(p1, q2, q1)) ||
				(o3 == 0 && segmentOverlap(p2, p1, q2)) ||
				(o4 == 0 && segmentOverlap(p2, q1, q2));
		return ((o1 != o2 && o3 != o4) || special);
	}

	private int orientation (Position p, Position q, Position r) {
		double orient =
				(q.getY() - p.getY()) * (r.getX() - q.getX()) -
				(q.getX() - p.getX()) * (r.getY() - q.getY());
		return orient == 0 ? 0 : (orient > 0 ? 1 : 2);
	}

	private boolean encapsulatesBounds(Unit inner, Bounds outer){
		return encapsulates(inner.getProperties().getBounds().getPositions(),
				outer.getPositions());
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

}