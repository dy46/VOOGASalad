package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

// PathForest is a set of disjoint PathGraphs that represent all the paths for an instance of a game
public class PathGraph {

	private List<PathNode> myGraphs;

	public PathGraph(List<PathNode> graphs){
		this.myGraphs = graphs;
	}

	public PathGraph() {
		myGraphs = new ArrayList<>();
	}

	public void addGraph(PathNode graph){
		myGraphs.add(graph);
	}

	public List<PathNode> getGraphs(){
		return myGraphs;
	}

	public List<Branch> getPaths(){
		return myGraphs.stream().map(p -> p.getPaths()).collect(Collectors.toList()).stream().flatMap(List<Branch>::stream).collect(Collectors.toList());
	}

	public PathNode getGraphByID(int ID){
		Optional<PathNode> graph = myGraphs.stream().filter(g -> g.getID() == (ID)).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}

	public Branch getPathByID(int ID){
		for(PathNode path : myGraphs){
			Branch branch = path.getBranchByID(ID);
			if(branch != null){
				return branch;
			}
		}
		return null;
	}

	public PathNode getGraphByPos(Position pos){
		Optional<PathNode> graph = myGraphs.stream().filter(g -> g.getPathByEdgePosition(pos) != null).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}
}