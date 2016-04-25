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
 * This class is a utility Artificial Intelligence searcher that allows for search problems.
 * A search problem is composed of a list of branches, which compose a "path graph".
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

	public List<Branch> getPathToAnyGoal(Position current){
		HashMap<Branch, List<Branch>> BFSVisitedMap = getBFSVisitedMap(myVisibility.getVisibilityBranches(), current);
		Iterator it = BFSVisitedMap.keySet().iterator();
		while(it.hasNext()){
			Branch start = (Branch) it.next();
			List<Branch> branchList = BFSVisitedMap.get(start);
			for(Branch branch : branchList){
				for(Position pos : branch.getPositions()){
					for(Position goal : myEngine.getCurrentLevel().getGoals()){
						if(pos.equals(goal)){
							List<Branch> path = getOrderedPath(branchList, start, branch);
							System.out.println("GOAL REACHED: " + goal+" FOR PATH: " + path);
							if(path != null){
								return path;
							}
						}
					}
				}
			}
		}
		return null;
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
		List<Branch> startBranches = myEngine.getBranchesAtPos(start);
		List<Branch> goalBranches = myEngine.getBranchesAtPos(goal);
		List<Branch> bestPath = null;
		for(Branch s : startBranches){
			for(Branch g: goalBranches){
				bestPath = dijkstrasShortestPath(s, g, visibilityBranches);
				if(bestPath != null){
					return bestPath;
				}
			}
		}
		return null;
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
		HashMap<Branch, List<Branch>> visitedMap = getBFSVisitedMap(visibilityBranches, current);
		if(visitedMap.keySet().size() == 0){
			return false;
		}
		for(List<Branch> visited : visitedMap.values()){
			for(Branch v : visited){
				if(v.getPositions().contains(goal)){
					return true;
				}
			}
		}
		return false;
	}

	private HashMap<Branch, List<Branch>> getBFSVisitedMap(List<Branch> visibilityBranches, Position current){
		if(!myVisibility.positionVisibleCheck(visibilityBranches, current)){
			return new HashMap<>();
		}
		List<Branch> startBranches = myEngine.getBranchesAtPos(current);
		HashMap<Branch, List<Branch>> visitedMap = new HashMap<>();
		for(Branch start : startBranches){
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
			visitedMap.put(start, visited);
		}
		return visitedMap;
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

	private List<Branch> getOrderedPath(List<Branch> branches, Branch start, Branch goal){
		return this.dijkstrasShortestPath(start, goal, branches);
	}

}