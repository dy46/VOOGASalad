package auth_environment.Models.Interfaces;

import java.util.List;

import game_engine.game_elements.Branch;

public interface IPathTabModel extends IWorkspaceModel {
	
	public void setPathWidth(double width);
	
	public double getPathWidth(); 
	
	public void submitBranch(); 
	
	public void printCurrentPositions(); 
	
	public void loadBranches(); 
	
	public List<Branch> getBranches();
	
	public void addNewBranch(double x, double y);
	
	public void continueFromLastPosition(double x, double y);
	
}
