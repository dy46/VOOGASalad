package game_engine.game_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.properties.Position;

import java.util.List;

/*
 * Internal API that will be used in order to represent paths 
 * for enemy movements.
 */

public class UnidirectionalBranch {

	private List<Position> myPositions;
	private boolean cycle;
	private List<UnidirectionalBranch> myNeighbors;
	private int myID;

	public UnidirectionalBranch(List<Position> positions){
		myPositions = positions;
		myNeighbors = new ArrayList<>();
		initialize();
	}

	public UnidirectionalBranch() {
		this.myPositions = new ArrayList<>();
		this.myNeighbors = new ArrayList<>();
		initialize();
	}

	public UnidirectionalBranch(List<Position> positions, List<UnidirectionalBranch> neighbors) {
		cycle = false;
		initialize(positions, neighbors);
	}

	public void initialize(){
		if(myPositions == null)
			myPositions = new ArrayList<>();
		if(myNeighbors == null)
			myNeighbors = new ArrayList<>();
	}

	public void initialize(List<Position> list, List<UnidirectionalBranch> neighbors){
		myPositions = list;
		myNeighbors = neighbors;
	}

	public void setCycle(boolean state){
		cycle = state;
	}
	
	public void addPosition(Position append){
		myPositions.add(append);
	}

	public UnidirectionalBranch copyBranch(){
		UnidirectionalBranch newPath = new UnidirectionalBranch();
		this.myPositions.forEach(t -> {
			newPath.addPosition(t.copyPosition());
		});
		newPath.addNeighbors(myNeighbors.stream().map(b -> b.copyBranch()).collect(Collectors.toList()));
		return newPath;
	}

	public boolean isUnitAtLastPosition(Unit u) {
		Position lastPos = myPositions.get(myPositions.size()-1);
		return u.getProperties().getPosition().getX() == lastPos.getX() &&
				u.getProperties().getPosition().getY() == lastPos.getY();
	}
	
	public Position getFirstPosition() {
		return myPositions.get(0);
	}

	public Position getLastPosition() {
		return myPositions.get(myPositions.size()-1);
	}

	public void addNeighbor(UnidirectionalBranch neighbor){
		this.myNeighbors.add(neighbor);
	}

	public void addPositions(List<Position> positions){
		for(Position pos : positions){
			addPosition(pos);
		}
	}

	public List<Position> getPositions(){
		return myPositions;
	}

	public List<UnidirectionalBranch> getNeighbors(){
		return myNeighbors;
	}

	public int getID(){
		return myID;
	}

	public List<Position> cutoffByPosition(Position pos){
		List<Position> cutoff = new ArrayList<>();
		for(int x=myPositions.indexOf(pos); x<myPositions.size(); x++){
			cutoff.add(myPositions.get(x));
			if(x != myPositions.indexOf(pos)){
				myPositions.remove(x);
				x--;
			}
		}
		return cutoff;
	}

	public void addNeighbors(List<UnidirectionalBranch> neighbors) {
		myNeighbors.addAll(neighbors);
	}

	public List<UnidirectionalBranch> removeNeighbors(List<UnidirectionalBranch> neighbors){
		List<UnidirectionalBranch> removed = new ArrayList<>();
		for(UnidirectionalBranch neighbor : neighbors){
			if(myNeighbors.contains(neighbor)){
				myNeighbors.remove(neighbor);
				removed.add(neighbor);
			}
		}
		return removed;
	}

	public String toString(){
//		return "Branch ID: " + myID+ " positions: " + myPositions;
		return "Branch ID: " + myID;
	}

	public int getLength(){
		return getPositions().size();
	}

	public List<UnidirectionalBranch> getForwardNeighbors(){
		List<UnidirectionalBranch> forwards = new ArrayList<>();
		for(UnidirectionalBranch b : myNeighbors){
			if(b.getFirstPosition().equals(getLastPosition())){
				forwards.add(b);
			}
		}
		return forwards;
	}

	public boolean isAccessible(Position p){
		for(UnidirectionalBranch b : getForwardNeighbors()){
			if(b.getPositions().contains(p)){
				return true;
			}
		}
		return false;
	}

	public boolean equals(UnidirectionalBranch branch){
		return branch.getPositions().equals(this.getPositions()) && (branch.getID() == this.getID());
	}

	public void removeNeighbor(UnidirectionalBranch b) {
		this.myNeighbors.remove(b);
	}

	public UnidirectionalBranch deepCopy(Map<UnidirectionalBranch, UnidirectionalBranch> isomorphism){
		UnidirectionalBranch copy = isomorphism.get(this);
	    if (copy == null) {
	        copy = new UnidirectionalBranch();
	        isomorphism.put(this, copy);
	        for (UnidirectionalBranch neighbor : this.myNeighbors) {
	            copy.addNeighbor(neighbor.deepCopy(isomorphism));
	        }
	    }
	    return copy;
	}

}