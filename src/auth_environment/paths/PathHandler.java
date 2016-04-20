package auth_environment.paths;

import java.util.ArrayList;
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
	/*
	 * Takes in a list of positions, generates the interpolated points, and adds them to a new list
	 * 
	 * @param	positions is the list of positions to be interpolated
	 * @param	cycle determines whether or not the list of positions should eventually loop back around
	 * @return	the list of interpolated positions
	 */
	private List<Position> getInterpolatedPositions(List<Position> positions, boolean cycle){
		List<Position> newList = new ArrayList<Position>();
		HashMap<Position, Position> nextPositions = fillPositionGaps(positions, cycle);
		if(!positions.isEmpty()){
			Position start = positions.get(0);
			newList.add(new Position(start.getX(), start.getY(), start.getZ()));
			Position curr = nextPositions.get(start);
			while(curr != null && !curr.equals(start) && !curr.equals(positions.get(0))){
				newList.add(new Position(curr.getX(), curr.getY(), curr.getZ()));
				curr = nextPositions.get(curr);
			}
		}
		return newList;
	}
	private HashMap<Position, Position> fillPositionGaps(List<Position> positions, boolean cycle){
		HashMap<Position, Position> nextPositions = new HashMap<>();
		int size = cycle ? positions.size() : positions.size() - 1;
		if(positions.size() < 1){
			return nextPositions;
		}
		double x = positions.get(0).getX();
		double y = positions.get(0).getY();
		double z = positions.get(0).getZ();
		for(int i = 0;i < size;i++){
			Position p1 = positions.get(i);
			Position p2 = positions.get((i+1)%positions.size());
			double vx = p2.getX() - p1.getX();
			double vy = p2.getY() - p1.getY();
			double vz = p2.getZ() - p1.getZ();
			double mag = Math.sqrt(vx*vx + vy*vy + vz*vz);
			vx /= mag;
			vy /= mag;
			vz /= mag;
			while((vx == 0 || (p2.getX() - x)/vx > 0 ) && (vy == 0 || (p2.getY() - y)/vy > 0) 
					&& (vz == 0 || (p2.getZ() - z)/vz > 0)){
				Position newPosition = new Position(x + vx, y + vy, z + vz);
				nextPositions.put(new Position(x, y, z), newPosition);
				x += vx;
				y += vy;
				z += vz;
			}
			nextPositions.put(new Position(x - vx, y - vy, z - vz), p2);
			x = p2.getX();
			y = p2.getY();
			z = p2.getZ();
		}
		return nextPositions;
	}
	
	
}
