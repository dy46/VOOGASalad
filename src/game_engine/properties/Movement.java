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

	public void setBranches(List<Branch> branches, Position currentPosition, double currentDirection){
		if(branches.get(0).equals(myCurrentBranch)){
			// edge case: current branch = new current branch but the current next branch != new next branch
			// need to turn around on current branch
			if(branches.size() > 1 && myBranches.size() > 1){
				if(!branches.get(1).equals(myBranches.get(1))){
					double degrees = getNextDegrees(currentPosition, myCurrentBranch.getFirstPosition());
					movingTowards = (degrees == currentDirection) ? myCurrentBranch.getLastPosition() : myCurrentBranch.getFirstPosition();
					this.myBranches = branches;
					myCurrentBranch = branches.get(0);
					return;
				}
			}
		}
		this.myBranches = branches;
		setCurrentBranch(branches.get(0), currentPosition, currentDirection);
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

	public void setCurrentBranch(Branch branch, Position currentPosition, double currentDirection) {
		myCurrentBranch = branch;
		initializeMovingTowards(currentPosition, currentDirection);
	}

	public void initializeCurrentBranch(Branch branch, Position currentPosition, double currentDirection){
		myCurrentBranch = branch;
		myBranches.add(myCurrentBranch);
		initializeMovingTowards(currentPosition, currentDirection);
	}

	public Double getNextDirection (Position currentPosition, double currentDirection) {
		Position nextPosition = getNextPosition(currentPosition, currentDirection);
		if(nextPosition == null){
			nextPosition = currentPosition;
		}
		return getNextDegrees(currentPosition, nextPosition);
	}

	private Double getNextDegrees(Position currentPosition, Position nextPosition){
		double dx = nextPosition.getX() - currentPosition.getX();
		double dy = nextPosition.getY() - currentPosition.getY();
		double newDir = Math.atan2((dy), (dx));
		double degreesDir = dx < 0 ? 270 - Math.toDegrees(newDir) : 90 - Math.toDegrees(newDir);
		return degreesDir;
	}

	public Position getNextPosition(Position currentPosition, double currentDirection){
		Position next = myCurrentBranch.getNextPosition(currentPosition, movingTowards);
		if(next == null){
			if(myBranches.get(myBranches.size()-1).equals(myCurrentBranch)){
				return null;
			}
			Branch nextBranch = myBranches.get(1 + myBranches.indexOf(myCurrentBranch));
			setCurrentBranch(nextBranch, currentPosition, currentDirection);
		}
		return next;
	}

	public void initializeMovingTowards(Position currentPosition, double currentDirection){
		if(currentPosition.equals(myCurrentBranch.getFirstPosition())){
			movingTowards = myCurrentBranch.getLastPosition();
		}
		else if(currentPosition.equals(myCurrentBranch.getLastPosition())){
			movingTowards = myCurrentBranch.getFirstPosition();
		}
		else{
			if(!myBranches.get(myBranches.size()-1).equals(myCurrentBranch)){
				Branch nextBranch = myBranches.get(myBranches.indexOf(myCurrentBranch) + 1);
				double degrees = getNextDegrees(currentPosition, nextBranch.getFirstPosition());
				movingTowards = (degrees == currentDirection) ? nextBranch.getFirstPosition() : nextBranch.getLastPosition();
			}
			else{
				movingTowards = (currentPosition.equals(myCurrentBranch.getFirstPosition()) ? myCurrentBranch.getLastPosition() : myCurrentBranch.getFirstPosition());
			}
		}
	}

}