package game_engine.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import game_engine.handlers.BranchHandler;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class BFSTuple {

	private List<Branch> myEngineBranches;
	private HashSet<Position> myVisited;
	private HashMap<Position, Position> myEdges;
	private HashMap<Position, Integer> myDistances;
	private BranchHandler myBranchHandler;

	public BFSTuple(List<Branch> engineBranches, HashSet<Position> visited,
			HashMap<Position, Position> edges, HashMap<Position, Integer> distances) {
		this.myEngineBranches = engineBranches;
		this.myVisited = visited;
		this.myEdges = edges;
		this.myDistances = distances;
		this.myBranchHandler = new BranchHandler();
	}

	public List<Branch> getPathTo(Position goal, Branch currBranch, Position currPos){
		if(!myVisited.contains(goal)){
			return null;
		}
		Stack<Position> pathStack = new Stack<>();
		Position current;
		for(current = goal; myDistances.get(current) != 0; current = myEdges.get(current)){
			pathStack.push(current);
		}
		pathStack.push(current);
		return createPath(pathStack, currBranch, currPos);
	}

	private List<Branch> createPath(List<Position> posList, Branch currBranch, Position currPos){
		List<Branch> path = new ArrayList<>();
		for(int x=0; x<posList.size()-1; x++){
			Position endA = posList.get(x);
			Position endB = posList.get(x+1);
			path.add(myEngineBranches.stream().filter(b -> b.getPositions().contains(endA) && b.getPositions().contains(endB)).findFirst().get());
		}
		if(path.size() > 1){
			if(currBranch != null){
				Branch partialBranch = myBranchHandler.getPartialBranch(currBranch, path.get(0), currPos);
				if(partialBranch != null){
					path.add(0, partialBranch);
					System.out.println("WITH PARTIAL: " + path);
				}
			}
		}
		return myBranchHandler.processBranches(path);
	}

	public boolean hasPathTo(Position pos) {
		return this.myVisited.contains(pos);
	}

}