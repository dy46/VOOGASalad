package auth_environment.backend;

/**
 * Created by BrianLin on 4/9/16.
 * Team member responsible: Brian
 *
 * This class holds the selected Game Element index and the coordinates of the selected tile. 
 * These values will be passed to the Backend using the getter methods.  
 * 
 * Pass this class to anything that will modify the selected Element or Coordinates. 
 */

public class SelectorModel implements ISelector {
	
	private int elementIndex;
	private double x;
	private double y; 
	
	public SelectorModel() {}; 

	@Override
	public void chooseElement(int index) {
		this.elementIndex = index; 
	}

	@Override
	public void chooseCoordinates(double x, double y) {
		this.x = x;
		this.y = y; 
	}

	@Override
	public int getElementIndex() {
		return this.elementIndex;
	}

	@Override
	public double getX() {
		return this.x;
	}
	
	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public void printCoordinates() {
		System.out.println("(" + this.x + "," + this.y + ")");
	}

	@Override
	public void printIndex() {
		System.out.println("GameElement index of: " + this.elementIndex);
	}

}
