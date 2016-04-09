package game_engine.properties;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public Bounds copyBounds() {
	    List<Position> newPositions = this.getPositions().stream().map(p -> p.copyPosition()).collect(Collectors.toList());
	    return new Bounds(newPositions);
	}
	
}