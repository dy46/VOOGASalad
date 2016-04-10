package auth_environment.view;

import auth_environment.backend.ISelector;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Team member responsible: Xander
 * Modifications from: Brian
 *
 * This represents the smallest unit of the Map Display. This tile consists of several layers: empty (blank), Terrain
 * and GameElement (stacked with GameElements on top). 
 * 
 * The key action is that on mouse click. 
 */

public class RecTile extends Rectangle {
	
	private double x;
	private double y; 
	
	private ISelector mySelector; 
	
	public RecTile(ISelector selector, double x, double y, double width, double height) {
		super(x, y, width, height);
		this.x = x;
		this.y = y; 
		this.mySelector = selector; 
		this.addListener();
	}

	private void addListener() {
		this.setOnMouseClicked(e -> this.recTileAction());
	}
	
	private void recTileAction() {
		mySelector.chooseCoordinates(this.x, this.y);
		mySelector.printCoordinates();
	}
}
