package auth_environment.paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.handlers.PositionHandler;
import game_engine.properties.Position;

public class MapHandler {
	
	
	private PathGraphFactory myPGF;
	private PositionHandler myPositionHandler;
	private List<Branch> myBranches;
	private List<Position> myGoals;
	private List<Position> mySpawns;

	public MapHandler(){
		myPGF = new PathGraphFactory();
		myPositionHandler = new PositionHandler();
		myBranches = new ArrayList<>();
		myGoals = new ArrayList<>();
		mySpawns = new ArrayList<>();
		createGrid();
	}

	public MapHandler(List<Branch> engineBranches, List<Position> spawns, List<Position> goals ){
		myBranches = engineBranches;
		this.mySpawns = spawns; 
		this.myGoals = goals; 
		myPGF = new PathGraphFactory(engineBranches);
		myPositionHandler = new PositionHandler();
		mySpawns = new ArrayList<>();
		myGoals = new ArrayList<>();
	}

	public void processPositions(List<Position> positions){
		List<Position> interpolatedPositions = myPositionHandler.getInterpolatedPositions(positions, false);
		myPGF.insertBranch(interpolatedPositions);
	}
	
	public void addSpawn(Position spawn){
		this.mySpawns.add(spawn);
	}
	
	public void addGoal(Position goal){
		this.myGoals.add(goal);
		processPositions(myPositionHandler.getInterpolatedPositions(Arrays.asList(goal), false));
	}

	public void createGrid(){
		double screenWidth = 500;
		double screenHeight = 500;
		addGoal(new Position(500, 500));
		addSpawn(new Position(0, 0));
		myPGF.insertGrid(screenWidth, screenHeight, getGridSquareSize(screenWidth, screenHeight));
	}

	private double getGridSquareSize(double screenWidth, double screenHeight){
		return screenWidth*screenHeight/5000;
	}


	public PathGraphFactory getPGF(){
		return myPGF;
	}

	public List<Branch> getBranches() {
		myBranches.addAll(myPGF.getBranches());
		return myBranches;
	}

	public List<Position> getGoals() {
		return myGoals;
	}
	
	public List<Position> getSpawns() {
		return mySpawns;
	}

}