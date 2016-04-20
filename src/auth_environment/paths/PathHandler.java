package auth_environment.paths;

import java.util.HashMap;
import java.util.List;

import game_engine.properties.Position;

public class PathHandler {
	
	PathGraphFactory myPGF;
	
	public PathHandler(){
		myPGF = new PathGraphFactory();
	}
	
	public void processStraightLine(List<Position> positions){
		HashMap<Position, Position> nextPositions = fillPositionGaps(positions, false);
		// add to PGF
		// myPGF.insertBranch(newPoints)
	}
	
	public void spline(List<Position> positions){
		List<Position> splinedPoints = getSplinedPoints(positions);
		myPGF.insertBranch(splinedPoints);
	}
	
	private List<Position> getSplinedPoints(List<Position> positions){
		return null;
	}

	private HashMap<Position, Position> fillPositionGaps(List<Position> positions, boolean cycle){
		HashMap<Position, Position> nextPositions = new HashMap<>();
		int size = cycle ? positions.size() : positions.size() - 1;
		if(positions.size() < 1){
			return nextPositions;
		}
		double x = positions.get(0).getX();
		double y = positions.get(0).getY();
		for(int i = 0;i < size;i++){
			Position p1 = positions.get(i);
			Position p2 = positions.get((i+1)%positions.size());
			double vx = p2.getX() - p1.getX();
			double vy = p2.getY() - p1.getY();
			double mag = Math.sqrt(vx*vx + vy*vy);
			vx /= mag;
			vy /= mag;
			while((vx == 0 || (p2.getX() - x)/vx > 0 ) && (vy == 0 || (p2.getY() - y)/vy > 0)){
				Position newPosition = new Position(x + vx, y + vy);
				nextPositions.put(new Position(x, y), newPosition);
				x += vx;
				y += vy;
			}
			nextPositions.put(new Position(x - vx, y - vy), p2);
			x = p2.getX();
			y = p2.getY();
		}
		return nextPositions;
	}
	
}
