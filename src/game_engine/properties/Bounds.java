package game_engine.properties;

import java.util.List;

public class Bounds {

	private List<Position> myPositions;
	
	public Bounds(List<Position> positions){
		this.myPositions = positions;
	}
	
	public List<Position> getPositions(){
		return myPositions;
	}
	
	public void setPositions(List<Position> positions){
		this.myPositions = positions;
	}
	
}