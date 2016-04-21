package auth_environment.paths;
import java.util.Arrays;
import java.util.List;

import game_engine.properties.Position;

public class PathHandler {
	
	private PathGraphFactory myPGF;
	private PositionHandler myPH;
	
	public PathHandler(){
		myPGF = new PathGraphFactory();
		myPH = new PositionHandler();
		insertTestBranches();
		insertGrid();
	}
	
	public PathHandler(PathGraphFactory pgf){
		myPGF = pgf;
		myPH = new PositionHandler();
		insertTestBranches();
		insertGrid();
	}
	
	public void processStraightLine(List<Position> positions){
		List<Position> interpolatedPositions = myPH.getInterpolatedPositions(positions, false);
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
		
		Position p14 = new Position(300, 30);
		Position p15 = new Position(300, 500);
		List<Position> b6 = Arrays.asList(p14, p15);
		processStraightLine(b6);
	}
	
	private List<Position> getSplinedPoints(List<Position> positions){
		return null;
	}
	
	public PathGraphFactory getPGF(){
		return myPGF;
	}
	
}