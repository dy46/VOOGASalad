package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

// PathForest is a set of disjoint PathNodes (or simply paths) that represent all the paths for an instance of a game
public class PathGraph {

	private List<PathNode> myPaths;

	public PathGraph(List<PathNode> paths){
		this.myPaths = paths;
	}

	public PathGraph() {
		myPaths = new ArrayList<>();
	}

	public void addPath(PathNode graph){
		myPaths.add(graph);
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
	
}