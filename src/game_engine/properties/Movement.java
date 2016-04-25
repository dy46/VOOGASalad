package game_engine.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.game_elements.Branch;
import game_engine.physics.DirectionHandler;

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

	public void setBranches(List<Branch> branches, Position currentPosition){
		this.myBranches = branches;
		setCurrentBranch(branches.get(0), currentPosition);
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
		initializeMovingTowards(currentPosition);
	}

	public void initializeCurrentBranch(Branch branch, Position currentPosition){
		myCurrentBranch = branch;
		myBranches.add(myCurrentBranch);
		initializeMovingTowards(currentPosition);
	}

	public Double getNextDirection (Position currentPosition) {
		Position nextPosition = getNextPosition(currentPosition);
		if(nextPosition == null){
			nextPosition = currentPosition;
		}
		return DirectionHandler.getDirectionBetween(currentPosition, nextPosition);
	}

	public Position getNextPosition(Position currentPosition){
//		System.out.println("MOVING TOWARDS: " + movingTowards);
		Position next = myCurrentBranch.getNextPosition(currentPosition, movingTowards);
		if(next == null){
			if(myBranches.get(myBranches.size()-1).equals(myCurrentBranch)){
				return null;
			}
			Branch nextBranch = myBranches.get(1 + myBranches.indexOf(myCurrentBranch));
			setCurrentBranch(nextBranch, currentPosition);
		}
		return next;
	}

	public void initializeMovingTowards(Position currentPosition){
		if(currentPosition.equals(myCurrentBranch.getFirstPosition())){
			movingTowards = myCurrentBranch.getLastPosition();
		}
		else if(currentPosition.equals(myCurrentBranch.getLastPosition())){
			movingTowards = myCurrentBranch.getFirstPosition();
		}
	}
	
	public Position getMovingTowards(){
		return this.movingTowards;
	}
	
	public void setMovingTowards(Position pos){
		this.movingTowards = pos;
	}

}