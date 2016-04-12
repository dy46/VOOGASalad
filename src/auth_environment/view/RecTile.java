package auth_environment.view;

import auth_environment.backend.ISelector;
import javafx.scene.shape.Rectangle;

public class RecTile extends Tile {
	
	private Rectangle myRectangle; 
	
	public RecTile(ISelector selector, double x, double y, double scaledX, double scaledY, double width, double height) {
		super(selector, x, y); 
		myRectangle = new Rectangle(scaledX, scaledY, width, height); 
	}
}
