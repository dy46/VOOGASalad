package game_engine.game_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game_engine.properties.Position;

/*
* Internal API that will be used in order to represent paths 
* for enemy movements.
*/
public class Path extends MapPiece{
	
	private List<Position> myPositions;
	private HashMap<Position, Position> nextPositions;
	private boolean cycle;
	
	public Path(String name){
		super(name);
		setID(getWorkspace().getIDFactory().createID(this));
		initialize(new ArrayList<>());
		cycle = false;
	}
	
	private void initialize(ArrayList<Position> list){
		myPositions = list;
		nextPositions = new HashMap<Position, Position>();
		setNextPositions();
	}
	public void setCycle(boolean state){
		cycle = state;
		setNextPositions();
	}
	private void setNextPositions(){
		int size = cycle ? myPositions.size() : myPositions.size() - 1;
		double x = myPositions.get(0).getX();
		double y = myPositions.get(0).getY();
		for(int i = 0;i < size;i++){
			Position p1 = myPositions.get(i);
			Position p2 = myPositions.get(i+1);
			double vx = p2.getX() - p1.getX();
			double vy = p2.getY() - p1.getY();
			double mag = Math.sqrt(vx*vx + vy*vy);
			vx /= mag;
			vy /= mag;
			while((p2.getX() - x)/vx > 0 && (p2.getY() - y)/vy > 0){
				nextPositions.put(new Position(x, y), new Position(x + vx, y + vy));
			}
			nextPositions.put(new Position(x - vx, y - vy), p2);
		}
	}
	
	
	/*
	* Gets the next position (point) in the path.
	* This will be used in order to determine which direction 
	* an Enemy needs to move in next.
	*
	* @return	The next Position in the list of Positions that represent the path being taken.
	*/
	public Position getNextPosition(Position currentPosition){
		return nextPositions.get(currentPosition);
	}
	
	public String toFile(){
		return getID();
	}
	
}