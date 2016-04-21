package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.paths.PathHandler;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * This Tab is for placing Paths on the Map 
 */

public class PathTabModel implements IPathTabModel {
	
	// TODO: Add a PathLibrary to AuthData 
	private IAuthEnvironment myAuthData;  
	
	private PathHandler myPathHandler = new PathHandler(); 
	
	// This is what populates the frontend list of Positions 
	private List<Branch> myBranches = new ArrayList<Branch>(); 
	
	// What's currently selected, will add to Branches
	private List<Position> myCurrentPositions = new ArrayList<Position>(); 
	
	private double myPathWidth; 
	
	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
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
	public void addPosition(double x, double y) {
		this.myCurrentPositions.add(new Position(x, y)); 
	}
	
	@Override
	public void submitBranch() {
		this.myPathHandler.processStraightLine(this.myCurrentPositions);
		this.myCurrentPositions.clear();
	}
	
	@Override
	public void printCurrentPositions() {
		this.myCurrentPositions.stream().forEach(s -> System.out.println(s.getX() + " " + s.getY()));
	}

	@Override
	public void loadBranches() {
		this.myBranches = this.myPathHandler.getPGF().getPathLibrary().getBranches();
	}
	
	@Override
	public List<Branch> getBranches() {
		this.loadBranches();
		return this.myBranches;
	}
}
