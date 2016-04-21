package auth_environment.Models.Interfaces;

public interface IPathTabModel extends IWorkspaceModel {
	
	public void setPathWidth(double width);
	
	public double getPathWidth(); 
	
	// Adds a Position to the current Branch (before submitting to Path Handler) 
	public void addPosition(double x, double y); 
	
	public void submitBranch(); 
	
}
