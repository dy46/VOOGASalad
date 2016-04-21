package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathGraph {

	private List<PathNode> myPaths;
	private List<Position> mySpawns;
	private List<Position> myGoals;
	private PathNode myPathGrid;

	public PathGraph(List<PathNode> paths){
		this.myPaths = paths;
	}

	public PathGraph() {
		myPaths = new ArrayList<>();
	}
	
	public void setPathGrid(PathNode pathGrid){
		if(myPaths.contains(myPathGrid)){
			myPaths.remove(myPathGrid);
		}
		this.myPathGrid = pathGrid;
	}

	public void addPath(PathNode graph){
		myPaths.add(graph);
	}
	
	public void addSpawn(Position spawn){
		mySpawns.add(spawn);
	}
	
	public void addGoal(Position goal){
		myGoals.add(goal);
	}

	public List<Branch> getBranches(){
		return myPaths.stream().map(p -> p.getBranches()).collect(Collectors.toList()).stream().flatMap(List<Branch>::stream).collect(Collectors.toList());
	}

	public PathNode getPathByID(int ID){
		Optional<PathNode> graph = myPaths.stream().filter(g -> g.getID() == (ID)).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}

	public Branch getBranchByID(int ID){
		for(PathNode path : myPaths){
			Branch branch = path.getBranchByID(ID);
			if(branch != null){
				return branch;
			}
		}
		return null;
	}

	public PathNode getPathByPos(Position pos){
		Optional<PathNode> path = myPaths.stream().filter(g -> g.getBranchesByEdgePosition(pos) != null).findFirst();
		return path.isPresent() ? path.get() : null;
	}

	public PathNode getLastPath(){
		return myPaths.get(myPaths.size()-1);
	}
	
	public List<PathNode> getPaths(){
		return myPaths;
	}

	public void addSpawns(List<Position> spawns) {
		this.mySpawns.addAll(spawns);
	}
	
	public void addGoals(List<Position> goals){
		this.myGoals.addAll(goals);
	}
	
	public void setSpawns(List<Position> spawns){
		this.mySpawns = spawns;
	}
	
	public void setGoals(List<Position> goals){
		this.myGoals = goals;
	}
	
	public PathNode getPathGrid(){
		return myPathGrid;
	}
	
}