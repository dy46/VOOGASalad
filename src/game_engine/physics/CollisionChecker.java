package game_engine.physics;

import java.util.ArrayList;
import java.util.List;

import game_engine.EngineWorkspace;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class CollisionChecker {

	public static List<Position> getUseableBounds (Bounds bounds, Position pos) {
		List<Position> newBounds = new ArrayList<Position>();
		for (Position p : bounds.getPositions()) {
			if(pos == null || bounds == null)
				System.out.println("P bounds: "+ p+" new pos: "+ pos);
			Position newP = new Position(p.getX() + pos.getX(), p.getY() + pos.getY());
			newBounds.add(newP);
		}
		return newBounds;
	}

	public static boolean collides (Unit a, Unit b) {
		List<Position> aPos = getUseableBounds(a.getProperties().getBounds(),
				a.getProperties().getPosition());
		return collides(aPos, b);
	}

	private static boolean collides (List<Position> bounds, Unit b) {
		List<Position> bPos = getUseableBounds(b.getProperties().getBounds(),
				b.getProperties().getPosition());
		for (int i = 0; i < bounds.size(); i++) {
			for (int j = 0; j < bPos.size(); j++) {
				if (intersect(bounds.get(i), bounds.get((i + 1) % bounds.size()), bPos.get(j),
						bPos.get((j + 1) % bPos.size()))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean simulatedObstacleCollisionCheck(List<Position> enemyBounds, List<Unit> obstacles){
		for(Unit obstacle : obstacles){
			if(collides(enemyBounds, obstacle)){
				return true;
			}
		}
		return false;
	}

	// check if q is on segment defined by p and r
	private static boolean segmentOverlap (Position p, Position q, Position r) {
		return (q.getX() <= Math.max(p.getX(), r.getX()) &&
				q.getX() >= Math.min(p.getX(), r.getX()) &&
				q.getY() <= Math.max(p.getY(), r.getY()) &&
				q.getY() >= Math.min(p.getY(), r.getY()));
	}

	private static boolean intersect (Position p1, Position q1, Position p2, Position q2) {
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

	private static int orientation (Position p, Position q, Position r) {
		double orient =
				(q.getY() - p.getY()) * (r.getX() - q.getX()) -
				(q.getX() - p.getX()) * (r.getY() - q.getY());
		return orient == 0 ? 0 : (orient > 0 ? 1 : 2);
	}

}