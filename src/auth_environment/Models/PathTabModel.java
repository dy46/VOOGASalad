package auth_environment.Models;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.paths.MapHandler;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * This Tab is for placing Paths on the Map 
 */

public class PathTabModel implements IPathTabModel {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	// TODO: Add a PathLibrary to AuthData 
	private IAuthEnvironment myAuthData;  
	private MapHandler myMapHandler; 
	private List<Branch> myVisualBranches;
	private List<Position> myCurrentBranch;
	private List<Position> myGoals;
	private List<Position> mySpawns;

	private double myPathWidth;

	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
		this.myMapHandler = new MapHandler(auth.getEngineBranches(), auth.getGridBranches(), auth.getVisualBranches());
		this.myVisualBranches = auth.getVisualBranches();
		this.myPathWidth = Double.parseDouble(this.myDimensionsBundle.getString("defaultPathWidth"));
		this.myCurrentBranch = new ArrayList<>();
		this.myGoals = auth.getGoals();
		this.mySpawns = auth.getSpawns();
	}

	// TODO: should all Paths have the same width? Where to set this? 
	@Override
	public void setPathWidth(double width) {
		this.myPathWidth = width; 
	}

	@Override
	public double getPathWidth() {
		return this.myPathWidth;
	}

	@Override
	public void submitBranch() {
		System.out.println("My current branch: " + myCurrentBranch);
		this.myMapHandler.processPositions(myCurrentBranch);
		myCurrentBranch.clear();
		this.loadBranches();
	}

	@Override
	public void printCurrentPositions() {
//		for(Branch b : myEngineBranches){
//			b.getPositions().stream().forEach(s -> System.out.println(s.getX() + " " + s.getY()));
//		}
	}

	@Override
	public void loadBranches() {
		this.myAuthData.setEngineBranches(this.myMapHandler.getEngineBranches());
		this.myAuthData.setVisualBranches(this.myVisualBranches);
		this.myAuthData.setGridBranches(this.myMapHandler.getGridBranches());
	}

	@Override
	public List<Branch> getEngineBranches() {
		return this.myAuthData.getEngineBranches();
	}

	@Override
	public List<Branch> getVisualBranches() {
		return this.myAuthData.getVisualBranches();
	}

	@Override
	public void addNewPosition(double x, double y) {
		myCurrentBranch.add(new Position(x, y));
	}

	@Override
	public void continueFromLastPosition(double x, double y) {
		myCurrentBranch.add(new Position(x, y));
	}

	@Override
	public void createGrid() {
		myMapHandler.createGrid();
	}

	@Override
	public void addNewSpawn(double x, double y) {
		this.mySpawns.add(new Position(x, y));
	}

	@Override
	public void addNewGoal(double x, double y) {
		this.myGoals.add(new Position(x, y));
	}

	@Override
	public List<Position> getGoals() {
		return myGoals;
	}

	@Override
	public List<Position> getSpawns() {
		return mySpawns;
	}

}