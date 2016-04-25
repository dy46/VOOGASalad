package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.CollisionChecker;
import game_engine.physics.EncapsulationChecker;
import game_engine.properties.Position;

public class VisibilityGraph {

	private EncapsulationChecker myEncapsulator;
	private GameEngineInterface myEngine;

	public VisibilityGraph(GameEngineInterface engine){
		myEncapsulator = new EncapsulationChecker();
		myEngine = engine;
	}

	public List<Branch> getVisibilityBranches() {
		return getVisibilityBranches(myEngine.getTowers());
	}

	public List<Branch> getSimulatedPlacementBranches(Unit obstacle){
		List<Unit> obstacles = new ArrayList<>();
		obstacles.add(obstacle);
		List<Unit> towerCopies = myEngine.getTowers().stream().map(t -> t.copyShallowUnit()).collect(Collectors.toList());
		obstacles.addAll(towerCopies);
		return getVisibilityBranches(obstacles);
	}

	public boolean isValidMap(List<Branch> visibilityBranches){
		for(Position goal : myEngine.getCurrentLevel().getGoals()){
			for(Position spawn : myEngine.getCurrentLevel().getSpawns()){
				if(!BFSPossible(visibilityBranches, spawn, goal)){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isAccessibleFrom(Branch b, Position p){
		List<Branch> visibilityBranches = getVisibilityBranches();
		return BFSPossible(visibilityBranches, b.getFirstPosition(), p);
	}

	public List<Branch> getShortestPath(Position current, List<Branch> visibilityBranches){
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
		return bestDijkstrasPath(current, closestGoal, visibilityBranches);
	}

	private List<Branch> getShortestPath(Position current, Position goal, List<Branch> visibilityBranches) {
		return bestDijkstrasPath(current, goal, visibilityBranches);
	}

	public List<Branch> bestDijkstrasPath(Position start, Position goal, List<Branch> visibilityBranches){
		if(!BFSPreCheck(visibilityBranches, start)){
			return null;
		}
		Branch startBranch = myEngine.findBranchForPos(start);
		Branch goalBranch = myEngine.findBranchForPos(goal);
		List<Branch> bestPath = dijkstrasShortestPath(startBranch, goalBranch, visibilityBranches);
		if(bestPath == null){
			return null;
		}
		return bestPath;
	}

	public List<Branch> dijkstrasShortestPath(Branch start, Branch goal, List<Branch> visibleBranches){
		HashMap<Branch, Branch> nextNodeMap = new HashMap<>();
		Branch currentNode = start;
		Queue<Branch> queue = new LinkedList<>();
		queue.add(currentNode);
		Set<Branch> visitedNodes = new HashSet<>();
		visitedNodes.add(currentNode);
		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			if (currentNode.equals(goal)) {
				break;
			} else {
				for (Branch nextNode : currentNode.getNeighbors()) {
					if (!visitedNodes.contains(nextNode)) {
						boolean isVisible = false;
						for(Branch v : visibleBranches){
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
		// TODO
		if(!shortestPath.get(0).equals(start)){
			shortestPath.set(0, start);
		}
		shortestPath.add(goal);
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

	private double getManhattanDistance(Position current, Position goal){
		return Math.sqrt(Math.pow(current.getX(), goal.getY()) + Math.pow(current.getY(), goal.getY()));
	}

	private List<Branch> getVisibilityBranches(List<Unit> obstacles){
		return filterObstacles(getFilteredBranches(obstacles));
	}

	private List<Branch> getFilteredBranches(List<Unit> obstacles){
		List<Branch> branchesToFilter = getBranchesToFilter(obstacles);
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		return copyBranchesToFilter;
	}

	private List<Branch> filterObstacles(List<Branch> branchesToFilter){
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
		List<Branch> copyBranches = branches.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		for(int y=0; y<branchesToFilter.size(); y++){
			for(int x=0; x<copyBranches.size(); x++){
				if(branchesToFilter.get(y).equals(copyBranches.get(x))){
					Branch removed = copyBranches.remove(x);
					x--;
					for(Branch b : copyBranches){
						for(int z=0; z < b.getNeighbors().size(); z++){
							if(b.getNeighbors().get(z).equals(removed)){
								b.getNeighbors().remove(z);
								z--;
							}
						}
					}
				}
			}	
		}
		return copyBranches;
	}

	private List<Branch> getBranchesToFilter(List<Unit> obstacles){
		Set<Branch> removalList = new HashSet<>();
		List<Branch> copyBranches =  myEngine.getBranches().stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		List<Unit> obstacleList = obstacles;
		List<Unit> copyObstacleList = obstacleList.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		for(Unit o : copyObstacleList){
			for(Branch b : copyBranches){
				for(Position pos : b.getPositions()){
					if(myEncapsulator.encapsulatesBounds(Arrays.asList(pos), CollisionChecker.getUseableBounds(o.getProperties().getBounds(), o.getProperties().getPosition()))){
						removalList.add(b);
					}
				}
			}
		}
		return new ArrayList<Branch>(removalList);
	}

	private boolean BFSPreCheck(List<Branch> visibilityBranches, Position spawn){
		Branch start = myEngine.findBranchForPos(spawn);
		boolean contained = false;
		for(Branch v : visibilityBranches){
			if(v.equals(start)){
				contained = true;
			}
		}
		return contained;
	}

	private boolean BFSPossible(List<Branch> visibilityBranches, Position current, Position goal){
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
		if(!BFSPreCheck(visibilityBranches, current)){
			return new ArrayList<>();
		}
		Branch start = myEngine.findBranchForPos(current);
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

	public boolean simulateEnemyPathFollowing(List<Branch> visibilityBranches, Unit obstacle) {
		List<Unit> enemies = myEngine.getActiveAIEnemies();
		for(Unit e : enemies){
			Position current = e.getProperties().getPosition();
			for(Position goal : myEngine.getCurrentLevel().getGoals()){
				List<Branch> shortestPath = getShortestPath(current, goal, visibilityBranches);
				if(!simulateEnemyBranchCollisions(e, shortestPath, obstacle)){
					return false;
				}
			}
		}
		return true;
	}

	private boolean simulateEnemyBranchCollisions(Unit enemy, List<Branch> pathBranches, Unit obstacle){
		List<Unit> obstacles = myEngine.getTowers();
		List<Unit> obstaclesCopy = obstacles.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		obstaclesCopy.add(obstacle.copyShallowUnit());
		for(Branch b : pathBranches){
			for(Position pos : b.getPositions()){
				List<Position> enemyBounds = CollisionChecker.getUseableBounds(enemy.getProperties().getBounds(), pos);
				if(CollisionChecker.simulatedObstacleCollisionCheck(enemyBounds, obstaclesCopy)){
					return false;
				}
			}
		}
		return true;
	}

}