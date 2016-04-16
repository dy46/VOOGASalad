package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

// PathForest is a set of disjoint PathGraphs that represent all the paths for an instance of a game
public class PathGraph {

	private List<Path> myPaths;

	public PathGraph(List<Path> graphs){
		this.myPaths = graphs;
	}

	public PathGraph() {
		myPaths = new ArrayList<>();
	}

	public void addPath(Path graph){
		myPaths.add(graph);
	}

	public List<Path> getGraphs(){
		return myPaths;
	}

	public List<Branch> getPaths(){
		return myPaths.stream().map(p -> p.getPaths()).collect(Collectors.toList()).stream().flatMap(List<Branch>::stream).collect(Collectors.toList());
	}

	public Path getGraphByID(int ID){
		Optional<Path> graph = myPaths.stream().filter(g -> g.getID() == (ID)).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}

	public Branch getPathByID(int ID){
		for(Path path : myPaths){
			Branch branch = path.getBranchByID(ID);
			if(branch != null){
				return branch;
			}
		}
		return null;
	}

	public Path getGraphByPos(Position pos){
		Optional<Path> graph = myPaths.stream().filter(g -> g.getPathByEdgePosition(pos) != null).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}
}