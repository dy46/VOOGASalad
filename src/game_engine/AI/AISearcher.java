package game_engine.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

/**
 * This class is a utility Artificial Intelligence searcher that allows for search problems.
 * A search problem is composed of a list of branches, which compose a "path graph".
 * 
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
		return getShortestPath(current, myVisibility.getVisibilityBranches());
	}

	public List<Branch> getShortestPath(Position current, List<Branch> visibilityBranches){
		BFSTuple tuple = this.getBFSPath(current, getGoals(), visibilityBranches);
		for(Position goal : getGoals()){
			List<Branch> path = tuple.getShortestPath(goal);
			if(path != null){
				return path;
			}
		}
		return null;
	}

	public BFSTuple getBFSPath(Position current, List<Position> goals, List<Branch> visibilityBranches){
		List<Position> goalCopies = goals.stream().map(g -> g.copyPosition()).collect(Collectors.toList());
		Queue<Position> queue = new LinkedList<>();
		HashMap<Position, Integer> distances = new HashMap<>();
		HashMap<Position, Position> edges = new HashMap<>();
		HashSet<Position> visited = new HashSet<>();
		getEndPoints(visibilityBranches).stream().forEach(p -> distances.put(p, Integer.MAX_VALUE));
		distances.put(current, 0);
		visited.add(current);
		queue.add(current);
		while(!queue.isEmpty()){
			Position next = queue.poll();
			for(Position adjacent : getNeighborPositions(visibilityBranches, next)){
				if(!visited.contains(adjacent)){
					edges.put(adjacent, next);
					distances.put(adjacent, distances.get(next) + 1);
					visited.add(adjacent);
					queue.add(adjacent);
					if(goalCopies.contains(adjacent)){
						goalCopies.remove(adjacent);
					}
					if(goalCopies.size() == 0){
						queue.clear();
						break;
					}
				}
			}
		}
		return new BFSTuple(visibilityBranches, current, visited, edges, distances);
	}

	//	private void trimFirstBranch(List<Branch> newPath, Position currPos){
	//		if(newPath == null || newPath.size() <= 1)
	//			return;
	//		Branch firstBranch = newPath.get(0);
	//		Branch secondBranch = newPath.get(1);
	//		int trimIndex = firstBranch.getPositions().indexOf(currPos);
	//		boolean trimRight = secondBranch.getEndPoints().contains(firstBranch.getFirstPosition());
	//		Branch newBranch = trimBranchAtIndex(trimIndex, trimRight, firstBranch);
	//		newPath.set(0, newBranch);
	//	}
	//	
	//	private Branch trimBranchAtIndex(int trimIndex, boolean trimRight, Branch branch){
	//		Branch copyBranch = branch.copyBranch();
	//		List<Position> copyPos = copyBranch.getPositions();
	//		List<Position> trimmedPos = trimRight ? copyPos.subList(0, trimIndex+1): copyPos.subList(trimIndex, copyPos.size());
	//		branch = new Branch(trimmedPos);
	//		branch.addNeighbors(copyBranch.getNeighbors());
	//		return branch;
	//	}

	private List<Position> getEndPoints(List<Branch> visibilityBranches){
		HashSet<Position> pointSet = new HashSet<>();
		for(Branch b : visibilityBranches){
			pointSet.add(b.getFirstPosition());
			pointSet.add(b.getLastPosition());
		}
		return new ArrayList<>(pointSet);
	}

	public boolean isValidSearchProblem(List<Branch> visibilityBranches){
		for(Unit u : this.myEngine.getUnitController().getUnitType("Enemy")){
			Branch currentBranch = u.getProperties().getMovement().getCurrentBranch();
			if(!visibilityBranches.contains(currentBranch)){
				return false;
			}
		}
		for(Position goal : myEngine.getLevelController().getCurrentLevel().getGoals()){
			for(Position spawn : myEngine.getLevelController().getCurrentLevel().getSpawns()){
				if(!BFSPossible(visibilityBranches, spawn, goal)){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValidSearchProblem(List<Branch> path, List<Branch> visibilityBranches) {
		for(Branch b : path){
			if(!visibilityBranches.contains(b)){
				return false;
			}
		}
		return true;
	}

	private List<Branch> getVisibleBranchesAtPos(List<Branch> visibility, Position current){
		List<Branch> branchesAtPos = new ArrayList<>();
		for(Branch b : visibility){
			if(b.getPositions().contains(current))
				branchesAtPos.add(b);
		}
		return branchesAtPos;
	}

	private List<Position> getNeighborPositions(List<Branch> visibility, Position current){
		List<Branch> neighboringBranches = getVisibleBranchesAtPos(visibility, current);
		List<Position> neighborPos = new ArrayList<>();
		for(Branch n : neighboringBranches){
			if(n.getFirstPosition().equals(current)) {
				neighborPos.add(n.getLastPosition());
			} else if(n.getLastPosition().equals(current)) {
				neighborPos.add(n.getFirstPosition());
			} else {
				neighborPos.add(n.getLastPosition());
				neighborPos.add(n.getFirstPosition());
			}
		}
		return neighborPos;
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
		if(!myVisibility.isPositionVisible(visibilityBranches, current)){
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

	private List<Position> getGoals(){
		return myEngine.getLevelController().getCurrentLevel().getGoals();
	}

}