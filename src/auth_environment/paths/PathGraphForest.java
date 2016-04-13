package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;

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

	public PathGraph getGraphByID(String ID){
		for(PathGraph graph : myGraphs){
			if(graph.getID().equals(ID)){
				return graph;
			}
		}
		return null;
	}

	public PathNode getPathByID(String ID){
		for(PathGraph graph : myGraphs){
			List<PathNode> paths = graph.getPathNodes(graph.getRoot());
			for(PathNode p : paths){
				if(p.getID().equals(ID)){
					return p;
				}
			}
		}
		return null;
	}

}