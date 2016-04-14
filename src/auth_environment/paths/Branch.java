package auth_environment.paths;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class Branch {

	private List<Position> myPositions;
	private List<Branch> myNeighbors;
	private int myID;

	public Branch(List<Position> positions, int ID){
		this.myID = ID;
		myPositions = positions;
		myNeighbors = new ArrayList<>();
	}

	public Branch(int ID) {
		this.myPositions = new ArrayList<>();
		this.myNeighbors = new ArrayList<>();
	}

	public void addNeighbor(Branch neighbor){
		this.myNeighbors.add(neighbor);
	}

	public void addPositions(List<Position> positions){
		this.myPositions.addAll(positions);
	}

	public List<Position> getPositions(){
		return myPositions;
	}

	public List<Branch> getNeighbors(){
		return myNeighbors;
	}

	public int getID(){
		return myID;
	}

	public List<Position> cutoffByPosition(Position pos){
		List<Position> cutoff = myPositions.subList(myPositions.indexOf(pos), myPositions.size());
		cutoff.clear();
		return cutoff;
	}

	public void addNeighbors(List<Branch> neighbors) {
		myNeighbors.addAll(neighbors);
	}

	public List<Branch> removeNeighbors(List<Branch> neighbors){
		List<Branch> removed = new ArrayList<>();
		for(Branch neighbor : neighbors){
			if(myNeighbors.contains(neighbor)){
				myNeighbors.remove(neighbor);
				removed.add(neighbor);
			}
		}
		return removed;
	}

}