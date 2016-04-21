package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import game_engine.properties.Position;

public class PathHandler {
	
	PathGraphFactory myPGF;
	
	public PathHandler(){
		myPGF = new PathGraphFactory();
//		insertTestBranches();
//		insertGrid();
	}
	
	public void processStraightLine(List<Position> positions){
		List<Position> interpolatedPositions = getInterpolatedPositions(positions, false);
		myPGF.insertBranch(interpolatedPositions);
	}
	
	public void spline(List<Position> positions){
		List<Position> splinedPoints = getSplinedPoints(positions);
		myPGF.insertBranch(splinedPoints);
	}
	
	private void insertGrid(){
		myPGF.createUnlimitedPathGraph(500, 500, 2);
	}
	
	private void insertTestBranches(){
		Position p1 = new Position(0, 30);
		Position p3 = new Position(200, 30);
		List<Position> b1 = Arrays.asList(p1, p3);
		processStraightLine(b1);
		
		Position p4 = new Position(200, 30);
		Position p5 = new Position(400, 30);
		Position p6 = new Position(400, 200);
		List<Position> b2 = Arrays.asList(p4, p5, p6);
		processStraightLine(b2);

		Position p7 = new Position(200, 30);
		Position p8 = new Position(200, 200);
		Position p9 = new Position(400, 200);
		List<Position> b3 = Arrays.asList(p7, p8, p9);
		processStraightLine(b3);

		Position p10 = new Position(400, 200);
		Position p11 = new Position(400, 525);
		List<Position> b4 = Arrays.asList(p10, p11);
		processStraightLine(b4);
		
		Position p12 = new Position(100, 30);
		Position p13 = new Position(100, 200);
		List<Position> b5 = Arrays.asList(p12, p13);
		processStraightLine(b5);
		
		Position p14 = new Position(300, 0);
		Position p15 = new Position(300, 500);
		List<Position> b6 = Arrays.asList(p14, p15);
		processStraightLine(b6);
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
	
	public PathGraphFactory getPGF(){
		return myPGF;
	}
	
}