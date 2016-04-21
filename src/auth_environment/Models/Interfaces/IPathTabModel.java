package auth_environment.Models.Interfaces;

import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public interface IPathTabModel extends IWorkspaceModel {
	
	public void setPathWidth(double width);
	
	public double getPathWidth(); 
	
	// Adds a Position to the current Branch (before submitting to Path Handler) 
	public void addPosition(double x, double y); 
	
	public void submitBranch(); 
	
	public void printCurrentPositions(); 
	
	public void loadBranches(); 
	
	public List<Branch> getBranches(); 
	
}
