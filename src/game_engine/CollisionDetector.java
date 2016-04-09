package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.CollidableUnit;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class CollisionDetector {
	private EngineWorkspace myEngine;

	public CollisionDetector(EngineWorkspace engine){
		myEngine = engine;
	}

	public void resolveEnemyCollisions(List<CollidableUnit> collideList){
		myEngine.getEnemies().forEach(t -> updateEnemies(t, collideList));
	}
	
	public void resolveTowerCollisions(List<CollidableUnit> collideList){
		//myEngine.getTowers().forEach(t -> updateTowers(t, terrainList));
	}

	// returns which Unit from the list collided with the target unit
	private void updateEnemies(Unit unit, List<CollidableUnit> collideChecks){
		ArrayList<Unit> removeList = new ArrayList<Unit>();
		for(int i = 0;i < collideChecks.size();i++){
			if(!(unit == collideChecks.get(i)) && collides(unit, collideChecks.get(i))){
				//				myEngine.remove(unit);
				//				myEngine.remove(collideChecks.get(i));
				unit.addAffectors(collideChecks.get(i).getAffectorsToApply());
				collideChecks.get(i).setElapsedTime(collideChecks.get(i).getTTL());
			}
		}
	}

	private List<Position> getUseableBounds(Unit u){
		List<Position> newBounds = new ArrayList<Position>();
		for(Position p: u.getProperties().getBounds().getPositions()){
			Position unitPos = u.getProperties().getPosition();
			Position newP = new Position(p.getX()+unitPos.getX(), p.getY()+unitPos.getY());
			newBounds.add(newP);
		}
		return newBounds;
	}
	private boolean collides(Unit a, Unit b){
		List<Position> aPos = getUseableBounds(a);
		List<Position> bPos = getUseableBounds(b);
		for(int i = 0;i < aPos.size();i++){
			for(int j = 0;j < bPos.size();j++){
				if(intersect(aPos.get(i), aPos.get((i+1)%aPos.size()), bPos.get(j), bPos.get((j+1)%bPos.size()))){
					return true;
				}
			}
		}
		return false;
	}
	// check if q is on segment defined by p and r
	private boolean segmentOverlap(Position p, Position q, Position r){
		return (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
				&& q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()));
	}
	private boolean intersect(Position p1, Position q1, Position p2, Position q2){
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
		boolean special = (o1 == 0 && segmentOverlap(p1, p2, q1)) || (o2 == 0 && segmentOverlap(p1, q2, q1)) ||
				(o3 == 0 && segmentOverlap(p2, p1, q2)) || (o4 == 0 && segmentOverlap(p2, q1, q2));
		return ((o1 != o2 && o3 != o4) || special);
	}

	private int orientation(Position p, Position q, Position r){
		double orient = (q.getY() - p.getY())*(r.getX() - q.getX()) - (q.getX() - p.getX())*(r.getY() - q.getY());
		return orient == 0 ? 0 : (orient > 0 ? 1 : 2);
	}

}
