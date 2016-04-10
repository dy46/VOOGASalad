package auth_environment.backend;

public interface ISelector {
	
	public void chooseElement(int index);
	
	public void chooseCoordinates(double x, double y); 
	
	// Returns index of the selected GameElement. 
	public int getElementIndex();
	
	// Returns X and Y coordinates of the selected Map Tile. 
	public double getX();
	public double getY(); 
	
	// For debugging and testing
	public void printIndex(); 
	public void printCoordinates(); 

}
