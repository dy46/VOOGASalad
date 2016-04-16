package game_engine.properties;

import java.util.List;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;

public class Movement {

	private List<Branch> myBranches;
	private int currentBranchIndex;
	private Branch currentBranch;

	public Movement(List<Branch> branches){
		this.myBranches = branches;
		currentBranchIndex = 0;
		if(branches.size() > 0)
			currentBranch = myBranches.get(0);
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
		return new Movement(this.myBranches.stream().map(b -> b.copyBranch()).collect(Collectors.toList()));
	}

	public Branch getCurrentBranch(){
		return currentBranch;
	}

	public Branch getLastBranch(){
		return myBranches.get(myBranches.size()-1);
	}

	public Branch getNextBranch() {
		if(getLastBranch().equals(currentBranch))
			return null;
		return myBranches.get(currentBranchIndex++);
	}

}