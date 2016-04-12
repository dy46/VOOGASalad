package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.ISelector;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RecTile extends Tile {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private double x;
	private double y;
	
	private Rectangle myRectangle; 
	
	public RecTile(ISelector selector, double width, double height) {
		super(selector); 
		myRectangle = new Rectangle(width, height);
	}
	
	public RecTile(ISelector selector, double x, double y, double scaledX, double scaledY, double width, double height) {
		super(selector);
		this.x = x;
		this.y = y; 
		myRectangle = new Rectangle(scaledX, scaledY, width, height); 
	}
	
	@Override
	protected void addListener() {
		this.myRectangle.setOnMouseClicked(e -> this.recTileAction());
	}
	
	private void recTileAction() {
		System.out.println("(" + this.x + "," + this.y + ")");
	}
	
	@Override
	protected void setImage(Image image) {
		this.myRectangle.setFill(new ImagePattern(image));
	}

	@Override
	public Shape getShape() {
		return this.myRectangle;
	}
}
