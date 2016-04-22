package game_engine.libraries;

import java.util.List;

import auth_environment.paths.PathGraph;
import auth_environment.paths.PathNode;
import game_engine.game_elements.Branch;

public class PathLibrary {

	private PathGraph myPathGraph;
	
	public PathLibrary(){
		myPathGraph = new PathGraph();
	}
	
	public PathLibrary(PathGraph pathGraph, PathNode pathGrid){
		this.myPathGraph = pathGraph;
	}
	
	public PathGraph getPathGraph(){
		return myPathGraph;
	}
	
	public List<PathNode> getPaths(){
		return myPathGraph.getPaths();
	}

	public List<Branch> getBranches(){
		return myPathGraph.getBranches();
	}

	public PathGraph getGraph(){
		return myPathGraph;
	}

	public int getLastPathID() {
		int maxPathID = Integer.MIN_VALUE;
		for(PathNode n : myPathGraph.getPaths()){
			int currID = n.getID();
			if(currID > maxPathID){
				maxPathID = currID;
			}
		}
		return maxPathID;
	}
	
	public int getLastBranchID() {
		int maxBranchID = Integer.MIN_VALUE;
		for(Branch b : myPathGraph.getBranches()){
			int currID = b.getID();
			if(currID > maxBranchID){
				maxBranchID = currID;
			}
		}
		return maxBranchID;
	}
	
}