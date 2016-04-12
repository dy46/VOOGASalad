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
	
	private Rectangle myRectangle; 
	
	public RecTile(ISelector selector, double x, double y, double scaledX, double scaledY, double width, double height) {
		super(selector, x, y); 
		myRectangle = new Rectangle(scaledX, scaledY, width, height); 
	}

	@Override
	protected void addListener() {
		this.myRectangle.setOnMouseClicked(e -> this.recTileAction());
	}
	
	private void recTileAction() {
		chooseAndPrint();
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
