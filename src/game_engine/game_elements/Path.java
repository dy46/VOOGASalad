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
	
	public Path(String name){
		super(name);
		setID(getWorkspace().getIDFactory().createID(this));
		initialize();
	}
	
	private void initialize(){
		myPositions = new ArrayList<>();
		nextPositions = new HashMap<Position, Position>();
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

	public String toString(){
		return "Path " + getID()+" Length: " + myPositions.size()+" positions";
	}
	
	public String toFile(){
		return getID();
	}
	
}