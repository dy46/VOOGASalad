package game_engine.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public static List<Position> getUseableBounds (Bounds bounds, Position pos) {
		List<Position> newBounds = new ArrayList<Position>();
		for (Position p : bounds.getPositions()) {
			Position newP = new Position(p.getX() + pos.getX(), p.getY() + pos.getY());
			newBounds.add(newP);
		}
		return newBounds;
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

}