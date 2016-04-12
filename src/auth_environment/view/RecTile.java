package auth_environment.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RecTile extends Tile {
	
	private double x;
	private double y;
	
	private Rectangle myRectangle; 
	
	public RecTile(double width, double height) {
		myRectangle = new Rectangle(width, height); 
	}
	
	public RecTile(double scaledX, double scaledY, double width, double height) {
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
