package game_engine.AI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class BFSTuple implements SearchTuple {

	private List<Branch> myEngineBranches;
	private HashSet<Position> myVisited;
	private HashMap<Position, Position> myEdges;
	private HashMap<Position, Integer> myDistances;
	private SearchHandler mySearchHandler;

	public BFSTuple(List<Branch> engineBranches, HashSet<Position> visited,
			HashMap<Position, Position> edges, HashMap<Position, Integer> distances) {
		this.myEngineBranches = engineBranches;
		this.myVisited = visited;
		this.myEdges = edges;
		this.myDistances = distances;
		this.mySearchHandler = new SearchHandler(myEngineBranches);
	}

	public List<Branch> getPathTo(Position goal, Branch currBranch, Position currPos){
	    System.out.println("GOAL HERE");
	        System.out.println(goal);
	        System.out.println("CURR BRANCH HERE");
	        System.out.println(currBranch);
	        System.out.println("CURR POS");
	        System.out.println(currPos);
	        System.out.println("MY VISITED");
	        System.out.println(myVisited);
	        System.out.println("ENGINE BRANCHES HERE");
	        System.out.println(myEngineBranches);
		if(!myVisited.contains(goal)){
			return null;
		}
		Stack<Position> pathStack = new Stack<>();
		Position current;
		for(current = goal; myDistances.get(current) != 0; current = myEdges.get(current)){
			pathStack.push(current);
		}
		pathStack.push(current);
		return mySearchHandler.createPath(pathStack, currBranch, currPos);
	}

	public boolean hasPathTo(Position pos) {
		return this.myVisited.contains(pos);
	}

}