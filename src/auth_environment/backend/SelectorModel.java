package auth_environment.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import game_engine.properties.Position;

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
	private List<Position> myPositions = new ArrayList<Position>();
	private Position myPosition; 
	
	public SelectorModel() {}; 

	@Override
	public void chooseElement(int index) {
		this.elementIndex = index; 
	}

	@Override
	public int getElementIndex() {
		return this.elementIndex;
	}

	@Override
	public void printPositions() {
		System.out.println("(" + this.myPosition.getX() + "," + this.myPosition.getY() + ")"); 
//		this.myPositions.stream().forEach(s -> System.out.println("(" + s.getX() + "," + s.getY()+ ")"));
	}
	
	@Override
	public void printMostRecentPosition() {
		System.out.println("(" + this.myPositions.get(myPositions.size()).getX() + "," + this.myPositions.get(myPositions.size()).getY()+ ")");
	}

	@Override
	public void printIndex() {
		System.out.println("GameElement index of: " + this.elementIndex);
	}

	@Override
	public void choosePosition(double x, double y) {
		this.myPosition = new Position(x,y); 
//		Position pos = new Position(x,y);
//		this.myPositions.add(pos); 
//		if (!this.checkContains(pos)) {
//			this.myPositions.add(pos);
//		}
	}

	// TODO: extract all the List of Position stuff away
//	private boolean checkContains(Position pos) {
//		boolean contains = false; 
//		for (Position p : this.myPositions) {
//			if (p.equals(pos)) {
//				this.myPositions.remove(this.myPositions.indexOf(p));
//				contains = true; 
//			}
//		}
//		return contains;
//	}

	@Override
	public Collection<Position> getPositions() {
		return this.myPositions;
	}

	@Override
	public Position getPosition() {
		return this.myPosition;
	}
}
