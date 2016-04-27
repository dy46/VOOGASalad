package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PositionHandler {
	
	/*
	 * Takes in a list of positions, generates the interpolated points, and adds them to a new list
	 * 
	 * @param	positions is the list of positions to be interpolated
	 * @param	cycle determines whether or not the list of positions should eventually loop back around
	 * @return	the list of interpolated positions
	 */
	public List<Position> getInterpolatedPositions(List<Position> positions, boolean cycle){
		List<Position> newList = new ArrayList<Position>();
		HashMap<Position, Position> nextPositions = fillPositionGaps(positions, cycle);
		if(!positions.isEmpty()){
			Position start = positions.get(0);
			newList.add(new Position(start.getX(), start.getY()));
			Position curr = nextPositions.get(start);
			while(curr != null && !curr.equals(start) && !curr.equals(positions.get(0))){
				newList.add(new Position(curr.getX(), curr.getY()));
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
		for(int i = 0;i < size;i++){
			Position p1 = positions.get(i);
			Position p2 = positions.get((i+1)%positions.size());
			double vx = p2.getX() - p1.getX();
			double vy = p2.getY() - p1.getY();
			double mag = Math.sqrt(vx*vx + vy*vy);
			vx /= mag;
			vy /= mag;
			while((vx == 0 || (p2.getX() - x)/vx > 0 ) && (vy == 0 || (p2.getY() - y)/vy > 0) ){
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
	
	public List<Branch> createPath(List<Position> posList, List<Branch> visibilityBranches){
		List<Branch> path = new ArrayList<>();
		for(int x=0; x<posList.size()-1; x++){
			Position endA = posList.get(x);
			Position endB = posList.get(x+1);
//			System.out.println(endA + "  " + endB);
			path.add(visibilityBranches.stream().filter(b -> b.getPositions().contains(endA) && b.getPositions().contains(endB)).findFirst().get());
		}
		return path;
	}
	
}