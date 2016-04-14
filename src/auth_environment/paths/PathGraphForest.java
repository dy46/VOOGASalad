package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import game_engine.properties.Position;

// PathForest is a set of disjoint PathGraphs that represent all the paths for an instance of a game
public class PathGraphForest {

	private List<PathGraph> myGraphs;

	public PathGraphForest(List<PathGraph> graphs){
		this.myGraphs = graphs;
	}

	public PathGraphForest() {
		myGraphs = new ArrayList<>();
	}
	
	public void addGraph(PathGraph graph){
		myGraphs.add(graph);
	}

	public List<PathGraph> getGraphs(){
		return myGraphs;
	}

	public PathGraph getGraphByID(int ID){
		Optional<PathGraph> graph = myGraphs.stream().filter(g -> g.getID() == (ID)).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}

	public PathNode getPathByID(int ID){
		for(PathGraph pathGraph : myGraphs){
			Optional<PathNode> graph = pathGraph.getPathNodes(pathGraph.getRoot()).stream().filter(p -> p.getID() == ID).findFirst();
			if(graph.isPresent())
				return graph.get();
		}
		return null;
	}
	
	public PathGraph getGraphByPos(Position pos){
		Optional<PathGraph> graph = myGraphs.stream().filter(g -> g.getPathByEdgePosition(pos) != null).findFirst();
		return graph.isPresent() ? graph.get() : null;
	}
}