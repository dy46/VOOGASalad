package auth_environment.paths;
import java.util.Arrays;
import java.util.List;

import auth_environment.dialogs.ConfirmationDialog;
import game_engine.properties.Position;

public class PathHandler {
	
	private PathGraphFactory myPGF;
	private PositionHandler myPositionHandler;
	
	public PathHandler(){
		myPGF = new PathGraphFactory();
		myPositionHandler = new PositionHandler();
		initializeGrid();
		insertTestBranches();
	}
	
	public PathHandler(PathGraphFactory pgf){
		myPGF = pgf;
		myPositionHandler = new PositionHandler();
	}
	
	public void processStraightLine(List<Position> positions){
		List<Position> interpolatedPositions = myPositionHandler.getInterpolatedPositions(positions, false);
		myPGF.insertBranch(interpolatedPositions);
	}
	
	public void spline(List<Position> positions){
		List<Position> splinedPoints = getSplinedPoints(positions);
		myPGF.insertBranch(splinedPoints);
	}
	
	private void initializeGrid(){
		String gridHeaderText = "You have the option to have a default grid drawn that won't limit other path creation.";
		String gridContextText = "Do you want this?";
		boolean confirmation = new ConfirmationDialog().getConfirmation(gridHeaderText, gridContextText);
		if(confirmation){
			// Change this
			double screenWidth = 500;
			double screenHeight = 500;
			myPGF.createUnlimitedPathGraph(500, 500, getGridSquareSize(screenWidth, screenHeight));
		}
	}
	
	private double getGridSquareSize(double screenWidth, double screenHeight){
		return screenWidth*screenHeight/25;
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
	}
	
	private List<Position> getSplinedPoints(List<Position> positions){
		return null;
	}
	
	public PathGraphFactory getPGF(){
		return myPGF;
	}
	
}