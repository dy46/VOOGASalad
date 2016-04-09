package auth_environment.backend;

/**
 * Created by BrianLin on 4/9/16.
 * Team member responsible: Brian
 *
 * This class holds the selected Game Element index and the coordinates of the selected tile. 
 * These values will be passed to the Backend using the getter methods.  
 */

public class SelectorModel implements ISelector {
	
	private int elementIndex;
	private int x;
	private int y; 

	@Override
	public void chooseElement(int index) {
		this.elementIndex = index; 
	}

	@Override
	public void chooseCoordinates(int x, int y) {
		this.x = x;
		this.y = y; 
	}

	@Override
	public int getElementIndex() {
		return this.elementIndex;
	}

	@Override
	public int getX() {
		return this.x;
	}
	
	@Override
	public int getY() {
		return this.y;
	}

}
