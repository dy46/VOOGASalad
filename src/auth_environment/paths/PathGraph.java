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

	public PathGraph(List<PathNode> paths){
		this.myPaths = paths;
	}

	public PathGraph() {
		myPaths = new ArrayList<>();
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

	public Branch getBranch(Branch newBranch) {
		for(PathNode p : myPaths){
			for(Branch b : p.getBranches()){
				if(b.equals(newBranch)){
					return b;
				}
			}
		}
		return null;
	}

	public PathNode getPath(PathNode path) {
		for(PathNode p : myPaths){
			if(p.equals(path)){
				return p;
			}
		}
		return null;
	}

}