package auth_environment.backend;

public interface ISelector {
	
	// Returns index of the selected GameElement. 
	public int getElementIndex();
	
	// Returns X and Y coordinates of the selected Map Tile. 
	public int getMapCoordinates();

}
