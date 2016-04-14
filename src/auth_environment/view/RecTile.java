package auth_environment.view;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class RecTile extends Tile {
	
	public double x;
	public double y;
	private int num;
	private Rectangle myRectangle; 
	public boolean isPath;
	public Text numDisplay;
	public RecTile(double width, double height) {
		super();
		myRectangle = new Rectangle(width, height); 
		isPath = false;
//		addListener();
	}
	
	public RecTile(double scaledX, double scaledY, double width, double height, double i, double j) {
		super();
		this.x = i;
		this.y = j;
		myRectangle = new Rectangle(scaledX, scaledY, width, height); 
		isPath = false;
		num = 1;
//		addListener();
		
	}
	
//	@Override
//	protected void addListener() {
//		this.myRectangle.setOnMouseClicked(e -> recTileAction());
//	}
//	
//	private void recTileAction() {
//		this.getShape().setFill(Color.GREEN);
//		isPath = true;
//	}
	
	@Override
	protected void setImage(Image image) {
		this.myRectangle.setFill(new ImagePattern(image));
	}

	@Override
	public Shape getShape() {
		return this.myRectangle;
	}
	
	public Text textDisplay(){
		this.textDisplay().setText(Integer.toString(num));
		return this.textDisplay();
	}
	
	public void setNum(int input){
		num = input;
	}
}
