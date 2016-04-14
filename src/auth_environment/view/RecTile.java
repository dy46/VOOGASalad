package auth_environment.view;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RecTile extends Tile {
	
	private double x;
	private double y;
	
	private Rectangle myRectangle; 
	public boolean isPath;
	public RecTile(double width, double height) {
		super();
		myRectangle = new Rectangle(width, height); 
		isPath = false;
		addListener();
	}
	
	public RecTile(double scaledX, double scaledY, double width, double height) {
		super();
		myRectangle = new Rectangle(scaledX, scaledY, width, height); 
		isPath = false;
		addListener();
	}
	
	@Override
	protected void addListener() {
		this.myRectangle.setOnMouseClicked(e -> recTileAction());
	}
	
	private void recTileAction() {
		this.getShape().setFill(Color.GREEN);
		isPath = true;
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
