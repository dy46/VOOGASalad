package game_engine.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.game_elements.Branch;

public class Movement {

	private List<Branch> myBranches;
	private Branch myCurrentBranch;
	private Position movingTowards;

	public Movement(List<Branch> branches){
		this.myBranches = branches;
		if(branches.size() > 0){
			myCurrentBranch = myBranches.get(0);
			if(myCurrentBranch.getPositions().size() > 0){
				movingTowards = myCurrentBranch.getLastPosition();
			}
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
		return new Movement(myBranches.stream().map(b -> b.copyBranch()).collect(Collectors.toList()));
	}

	public Branch getCurrentBranch(){
		return myCurrentBranch;
	}

	public Branch getLastBranch(){
		if(myBranches.size() == 0)
			return null;
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

	public void setCurrentBranch(Branch branch, Position currentPosition) {
		myCurrentBranch = branch;
		myBranches.add(myCurrentBranch);
		initializeMovingTowards(currentPosition);
	}

	public Double getNextDirection (Position currentPosition) {
		Position nextPosition = getNextPosition(currentPosition);
		if(nextPosition == null){
			nextPosition = currentPosition;
		}
		double dx = nextPosition.getX() - currentPosition.getX();
		double dy = nextPosition.getY() - currentPosition.getY();
		double newDir = Math.atan2((dy), (dx));
		double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
		return degreesDir;
	}

	public Position getNextPosition(Position currentPosition){
		Position next = myCurrentBranch.getNextPosition(currentPosition, movingTowards);
		if(next == null){
			if(myBranches.get(myBranches.size()-1).equals(myCurrentBranch)){
				return null;
			}
			Branch nextBranch = myBranches.get(1 + myBranches.indexOf(myCurrentBranch));
			if(nextBranch.getFirstPosition().equals(currentPosition)){
				movingTowards = nextBranch.getLastPosition();
				myCurrentBranch = nextBranch;
				return nextBranch.getFirstPosition();
			}
			else{
				myCurrentBranch = nextBranch;
				movingTowards = nextBranch.getFirstPosition();
				return nextBranch.getLastPosition();
			}
		}
		return next;
	}
	
	public void initializeMovingTowards(Position currentPosition){
		if(currentPosition.equals(myCurrentBranch.getFirstPosition())){
			movingTowards = myCurrentBranch.getLastPosition();
		}
		else
			movingTowards = myCurrentBranch.getFirstPosition();
	}

}