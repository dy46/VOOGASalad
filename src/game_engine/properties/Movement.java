package game_engine.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;

public class Movement {

	private List<Branch> myBranches;
	private Branch myCurrentBranch;
	private Position currentPosition;

	public Movement(List<Branch> branches, Position spawn){
		this.myBranches = branches;
		if(branches.size() > 0)
			myCurrentBranch = myBranches.get(0);
	}

	public Movement(List<Branch> branches){
		this.myBranches = branches;
		if(branches.size() > 0){
			myCurrentBranch = myBranches.get(0);
		}
	}
	
	public Movement(Position spawn){
		this.myBranches = new ArrayList<>();
	}

	public List<Branch> getBranches(){
		return myBranches;
	}

	public void setBranches(List<Branch> branches){
		this.myBranches = branches;
	}

	public Movement copyMovement(){
		return new Movement(this.myBranches.stream().map(b -> b.copyBranch()).collect(Collectors.toList()));
	}

	public Branch getCurrentBranch(){
		return myCurrentBranch;
	}

	public Branch getLastBranch(){
		return myBranches.get(myBranches.size()-1);
	}

	public Branch getNextBranch() {
		if(getLastBranch().equals(myCurrentBranch)){
			myCurrentBranch = null;
			return null;
		}
		int curentBranchIndex = myBranches.indexOf(myCurrentBranch);
		myCurrentBranch = myBranches.get(curentBranchIndex + 1);
		return myCurrentBranch;
	}

	public void setCurrentBranch(Branch branch) {
		myCurrentBranch = branch;
	}
	
	public Double getNextDirection () {
		Position nextPosition = getNextPosition();
		if(nextPosition == null){
			nextPosition = currentPosition;
		}
		double dx = nextPosition.getX() - currentPosition.getX();
		double dy = nextPosition.getY() - currentPosition.getY();
		double newDir = Math.atan2((dy), (dx));
		double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
		return degreesDir;
	}
	
	public Position getNextPosition(){
		return null;
	}

}