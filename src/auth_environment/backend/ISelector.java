package auth_environment.backend;

import java.util.Collection;

import game_engine.properties.Position;

public interface ISelector {
	
	// Single click
	public void chooseElement(int index);
	
	// Single click
	public void chooseCoordinates(double x, double y); 
	
	// Returns index of the selected GameElement. 
	public int getElementIndex();
	
	// Returns X and Y coordinates of the selected Map Tile. 
	public double getX();
	public double getY(); 
	public Position getPosition(); 
	
	// For debugging and testing
	public void printIndex(); 
	public void printCoordinates(); 
	
	// Maintain a List of selected points (SHIFT + click), used for Terrain and Path
	public void addCoordinates(double x, double y);
	public Collection<Position> getCoordinates(); 

}
