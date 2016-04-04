package auth_environment.backend;

import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class holds Game Elements. Elements can be organized in Aisles. 
 */

public interface ILibrary {
	
	// Retrieves a GameElement with the given Type and Name. 
	public GameElement pickElement(String elementType, String elementName); 
	
	// Adds a GameElement to the Library. 
	public void addElement(GameElement element); 
	
	// Opens an instance of the corresponding View to display the contents of this Library. 
	public void display(); 

}
