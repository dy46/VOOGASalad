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
	
	private PathHandler myPathHandler; 
	
	// This is what populates the frontend list of Positions 
	private List<Branch> myBranches; 
	
	// What's currently selected, will add to Branches.
	// For now, should only contain TWO positions
	private List<Position> myCurrentPositions;
	
	private double myPathWidth; 
	
	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
		this.myPathHandler = new PathHandler();
		this.myBranches = new ArrayList<Branch>(); 
		this.myCurrentPositions = new ArrayList<Position>(); 
		this.myBranches = auth.getPathBranches();
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
		this.myPathHandler.processStraightLine(this.myCurrentPositions);
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

	@Override
	public void addNewPosition(double x, double y) {
//		if (this.myCurrentPositions.size() > 0) {
//			this.submitBranch();
//		}
		this.myCurrentPositions.clear();
		this.myCurrentPositions.add(new Position(x, y)); 
	}

	@Override
	public void continueFromLastPosition(double x, double y) {
		if (this.myCurrentPositions.size() > 1) {
			this.myCurrentPositions.remove(0);
		}
		this.myCurrentPositions.add(new Position(x, y));
		this.submitBranch();
	}
}
