package game_engine.AI;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import auth_environment.paths.PositionHandler;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class BFSTuple {
	
	private List<Branch> myVisibilityBranches;
	private Position myCurrent;
	private HashSet<Position> myVisited;
	private HashMap<Position, Position> myEdges;
	private HashMap<Position, Integer> myDistances;

	public BFSTuple(List<Branch> visibilityBranches, Position current, HashSet<Position> visited,
			HashMap<Position, Position> edges, HashMap<Position, Integer> distances) {
		this.myVisibilityBranches = visibilityBranches;
		this.myVisited = visited;
		this.myEdges = edges;
		this.myDistances = distances;
	}

	public List<Branch> getShortestPath(Position goal){
		if(!myVisited.contains(goal)){
			return null;
		}
		Stack<Position> path = new Stack<>();
		for(myCurrent = goal; myDistances.get(myCurrent) != 0; myCurrent = myEdges.get(myCurrent)){
			path.push(myCurrent);
		}
		path.push(myCurrent);
		Collections.reverse(path);
		return new PositionHandler().createPath(path, myVisibilityBranches);
	}

}