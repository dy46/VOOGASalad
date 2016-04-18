package game_engine.properties;

import java.util.List;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;

public class Movement {

	private List<Branch> myBranches;
	private Branch myCurrentBranch;
	private Position mySpawn;

	public Movement(List<Branch> branches, Position spawn){
		this.myBranches = branches;
		this.mySpawn = spawn;
		if(branches.size() > 0)
			myCurrentBranch = myBranches.get(0);
	}

	public Movement(List<Branch> branches){
		this.myBranches = branches;
		if(branches.size() > 0){
			myCurrentBranch = myBranches.get(0);
			if(myCurrentBranch.getPositions().size() > 0)
				mySpawn = myCurrentBranch.getFirstPosition();
			else
				mySpawn = new Position(0,0);
		}
	}

	public List<Branch> getBranches(){
		return myBranches;
	}

	public void setBranches(List<Branch> branches){
		this.myBranches = branches;
	}

	public boolean isUnitAtLastPosition(Unit u) {
		return getLastBranch().isUnitAtLastPosition(u);
	}

	public Movement copyMovement(){
		return new Movement(this.myBranches.stream().map(b -> b.copyBranch()).collect(Collectors.toList()), mySpawn.copyPosition());
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

	public Position getSpawn(){
		return mySpawn;
	}

	public void setSpawn(Position spawn){
		this.mySpawn = spawn;
	}

}