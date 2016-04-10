package auth_environment.backend;

import java.util.Collection;

import game_engine.properties.Position;

public interface ISelector {
	
	// Single click (only) 
	public void chooseElement(int index);
	
	// Returns index of the selected GameElement (we will only have one GameElement selected at a time) 
	public int getElementIndex();
	
	// For debugging and testing
	public void printIndex(); 
	public void printPosition(); 
	
	public void choosePosition(double x, double y);
	public Position getPosition(); 
}
