package auth_environment.backend;

public interface ISelector {
	
	public void chooseElement(int index);
	
	public void chooseCoordinates(int x, int y); 
	
	// Returns index of the selected GameElement. 
	public int getElementIndex();
	
	// Returns X and Y coordinates of the selected Map Tile. 
	public int getX();
	public int getY(); 

}
