package game_engine.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * This class is a utility Artificial Intelligence searcher that allows for search problems using the path graph in the game engine.
 * A search problem is composed of a list of branches, possible starting points, and goals and tests for valid path finding.
 * @author adamtache
 *
 */

public class AISearcher {

	private GameEngineInterface myEngine;
	private VisibilityHandler myVisibility;
	
	public AISearcher(GameEngineInterface engine){
		this.myEngine = engine;
		this.myVisibility = new VisibilityHandler(engine);
	}
	
	public List<Branch> getShortestPath(Position current){
		List<Position> goals = new ArrayList<>();
		goals.addAll(myEngine.getCurrentLevel().getGoals());
		List<Position> sortedGoals = manhattanDistanceSort(current, goals);
		if(sortedGoals.size() == 0){
			return null;
		}
		Position closestGoal = sortedGoals.remove(0);
		while(closestGoal == null){
			closestGoal = sortedGoals.remove(0);
			if(closestGoal != null){
				break;
			}
		}
		return getShortestPathToGoal(current, closestGoal, myVisibility.getVisibilityBranches());
	}

	public List<Branch> getShortestPathToGoal(Position start, Position goal, List<Branch> visibilityBranches){
		if(!myVisibility.positionVisibleCheck(visibilityBranches, start)){
			return null;
		}
		Branch startBranch = myEngine.getNearestBranch(start);
		Branch goalBranch = myEngine.getNearestBranch(goal);
		List<Branch> bestPath = dijkstrasShortestPath(startBranch, goalBranch, visibilityBranches);
		if(bestPath == null){
			return null;
		}
		return bestPath;
	}
	
	public boolean isSearchPossible(Branch b, Position p){
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches();
		return BFSPossible(visibilityBranches, b.getFirstPosition(), p);
	}
	
	public boolean isValidSearchProblem(List<Branch> visibilityBranches){
		for(Position goal : myEngine.getCurrentLevel().getGoals()){
			for(Position spawn : myEngine.getCurrentLevel().getSpawns()){
				if(BFSPossible(visibilityBranches, spawn, goal)){
					return false;
				}
			}
		}
		return true;
	}

	public List<Branch> dijkstrasShortestPath(Branch start, Branch goal, List<Branch> visibilityBranches){
		HashMap<Branch, Branch> nextNodeMap = new HashMap<>();
		Branch currentNode = start;
		Queue<Branch> queue = new LinkedList<>();
		queue.add(currentNode);
		Set<Branch> visitedNodes = new HashSet<>();
		visitedNodes.add(currentNode);
		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			if(currentNode == null){
				break;
			}
			if (currentNode.equals(goal)) {
				break;
			} else {
				for (Branch nextNode : currentNode.getNeighbors()) {
					if (!visitedNodes.contains(nextNode)) {
						boolean isVisible = false;
						for(Branch v : visibilityBranches){
							if(v.equals(nextNode)){
								isVisible = true;
							}
						}
						if(isVisible){
							queue.add(nextNode);
							visitedNodes.add(nextNode);
							nextNodeMap.put(currentNode, nextNode);
						}
					}
				}
			}
		}
		if (!currentNode.equals(goal)) {
			return null;
		}
		List<Branch> shortestPath = new LinkedList<>();
		for (Branch node = start; node != null; node = nextNodeMap.get(node)) {
			shortestPath.add(node);
		}
		return shortestPath;
	}

	private List<Position> manhattanDistanceSort(Position current, List<Position> goals){
		TreeMap<Double, Position> manhattanDistanceMap = new TreeMap<>();
		for(Position goal : goals){
			manhattanDistanceMap.put(getManhattanDistance(current, goal), goal);
		}
		List<Position> sorted = new ArrayList<>();
		Iterator it = manhattanDistanceMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<Double, Position> entry = (Entry<Double, Position>) it.next();
			sorted.add(entry.getValue());
		}
		return sorted;
	}

	public double getManhattanDistance(Position current, Position goal){
		return Math.sqrt(Math.pow(current.getX(), goal.getY()) + Math.pow(current.getY(), goal.getY()));
	}
	
	public boolean BFSPossible(List<Branch> visibilityBranches, Position current, Position goal){
		List<Branch> visited = getBFSVisited(visibilityBranches, current, goal);
		if(visited.size() == 0){
			return false;
		}
		for(Branch b : visited){
			if(b.getPositions().contains(goal)){
				return true;
			}
		}
		return false;
	}

	private List<Branch> getBFSVisited(List<Branch> visibilityBranches, Position current, Position goal){
		if(!myVisibility.positionVisibleCheck(visibilityBranches, current)){
			return new ArrayList<>();
		}
		Branch start = myEngine.getNearestBranch(current);
		Branch copyStart = start.copyBranch();
		Queue<Branch> queue = new LinkedList<>();
		List<Branch> visited = new ArrayList<>();
		queue.add(copyStart);
		while(!queue.isEmpty()){
			Branch branch = (Branch) queue.remove();
			Branch child = null;
			while((child = getUnvisitedChildNode(branch, visited, visibilityBranches)) != null){
				visited.add(child);
				queue.add(child);
			}
		}
		return visited;
	}
	
	private Branch getUnvisitedChildNode(Branch branch, List<Branch> visited, List<Branch> visible) {
		List<Branch> neighbors = branch.getNeighbors();
		List<Branch> visibleNeighbors = new ArrayList<>();
		for(Branch b : neighbors){
			for(Branch v : visible){
				if(!visited.contains(b)){
					if(b.equals(v)){
						visibleNeighbors.add(b);
					}
				}
			}
		}
		if(visibleNeighbors.size() == 0){
			return null;
		}
		return visibleNeighbors.get(0);
	}
	
}