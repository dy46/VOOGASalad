package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
					//					System.out.println("NOT VALIDATED");
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

	public List<Branch> getShortestPath(Position current){
		List<Position> goals = new ArrayList<>();
		List<Position> sortedGoals = manhattanDistanceSort(current, goals);
		if(sortedGoals.size() == 0){
			return null;
		}
		Position closestGoal = sortedGoals.remove(0);
		while(closestGoal == null){
			closestGoal = sortedGoals.remove(0);
			if(closestGoal != null){
				return bestDijkstrasPath(current, closestGoal);
			}
		}
		return bestDijkstrasPath(current, closestGoal);
	}

	public List<Branch> bestDijkstrasPath(Position start, Position goal){
		if(!BFSPreCheck(getVisibilityBranches(), start)){
			return null;
		}
		Branch startBranch = myEngine.findBranchForPos(start);
		Branch goalBranch = myEngine.findBranchForPos(start);
		List<Branch> bestPath = getShortestPath(startBranch, goalBranch);
		return bestPath;
	}

	public List<Branch> getShortestPath(Branch start, Branch goal){
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
						queue.add(nextNode);
						visitedNodes.add(nextNode);
						nextNodeMap.put(currentNode, nextNode);
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
			sorted.add((Position) it.next());
		}
		return sorted;
	}

	private double getManhattanDistance(Position current, Position goal){
		return Math.sqrt(Math.pow(current.getX(), goal.getY()) + Math.pow(current.getY(), goal.getY()));
	}

	private List<Branch> getVisibilityBranches(List<Unit> obstacles){
		List<Branch> branchesToFilter = getBranchesToFilter(obstacles);
		System.out.println("TO FILTER: " + branchesToFilter);
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
		List<Branch> copyBranches = branches.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		for(int y=0; y<copyBranchesToFilter.size(); y++){
			for(int x=0; x<copyBranches.size(); x++){
				if(copyBranchesToFilter.get(y).equals(copyBranches.get(x))){
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
					//					System.out.println("POS: " + pos+" UNIT POS: " + o.getProperties().getPosition()+" BOUNDS: "+ CollisionChecker.getUseableBounds(o.getProperties().getBounds(), o.getProperties().getPosition()));
					if(myEncapsulator.encapsulatesBounds(Arrays.asList(pos), CollisionChecker.getUseableBounds(o.getProperties().getBounds(), o.getProperties().getPosition()))){
						//						System.out.println("ENCAPSULATED");
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

	private boolean BFSPossible(List<Branch> visibilityBranches, Position spawn, Position goal){
		List<Branch> visited = getBFSVisited(visibilityBranches, spawn, goal);
		//		System.out.println("BFS VISITED: " + visited);
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

	private List<Branch> getBFSVisited(List<Branch> visibilityBranches, Position spawn, Position goal){
		if(!BFSPreCheck(visibilityBranches, spawn)){
			return new ArrayList<>();
		}
		Branch start = myEngine.findBranchForPos(spawn);
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
		List<Unit> enemies = myEngine.getEnemies();
		for(Unit e : enemies){
			Position current = e.getProperties().getPosition();
			for(Position goal : myEngine.getCurrentLevel().getGoals()){
				List<Branch> BFSvisited = getBFSVisited(visibilityBranches, current, goal);
				if(!simulateEnemyBranchCollisions(e, BFSvisited, obstacle)){
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
				if(!CollisionChecker.simulatedObstacleCollisionCheck(enemyBounds, obstaclesCopy)){
					return false;
				}
			}
		}
		return true;
	}

}