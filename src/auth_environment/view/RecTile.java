package auth_environment.view;

import auth_environment.backend.IElementHolder;
import auth_environment.backend.ISelector;
import game_engine.game_elements.GameElement;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
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

public class RecTile extends Rectangle implements IElementHolder {
	
	private double x;
	private double y; 
	
	private GameElement myElement; 
	
	private ISelector mySelector; 
	
	public RecTile(ISelector selector, double x, double y, double scaledX, double scaledY, double width, double height) {
		super(scaledX, scaledY, width, height);
		this.x = x;
		this.y = y; 
		this.mySelector = selector; 
		this.addListener();
	}

	private void addListener() {
		this.setOnMouseClicked(e -> this.recTileAction());
	}
	
	private void recTileAction() {
		mySelector.choosePosition(this.x, this.y);
		mySelector.printPosition();
	}
	
	@Override
	public void update(GameElement element) {
		this.myElement = element; 
	}

	@Override
	public GameElement unload() {
		return this.myElement;
	}
	
	public void setImage(Image image) {
		this.setFill(new ImagePattern(image));
	}
}
