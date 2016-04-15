package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathNode {

	private List<Branch> myBranches;
	private int myID;
	
	public PathNode(int pathID){
		this.myID = pathID;
		myBranches = new ArrayList<>();
	}
	
	public void addBranch(Branch branch){
		myBranches.add(branch);
	}
	
	public Branch getBranchByID(int ID){
		Optional<Branch> branch = myBranches.stream().filter(b -> b.getID() == ID).findFirst();
		return branch.isPresent() ? branch.get() : null;
	}
	
	public List<Branch> getPathNodes(Branch branch){
		List<Branch> nodes = branch.getNeighbors().stream().filter(n -> getPathNodes(n) != null).collect(Collectors.toList());
		nodes.add(branch);
		return nodes;
	}
	
	public List<Branch> getPaths(){
		return myBranches.stream().map(p -> new Branch(p.getID()+"", p.getPositions(), p.getNeighbors())).collect(Collectors.toList());
	}
	
	public int getID(){
		return myID;
	}
	
	public List<Branch> getPathByEdgePosition(Position pos){
		return myBranches.stream().filter(
				n -> n.getPositions().get(0).equals(pos) || n.getPositions().get(n.getPositions().size()-1).equals(pos))
				.collect(Collectors.toList());
	}
	
	public Branch getPathByMidPosition(Position pos){
		Optional<Branch> graph = myBranches.stream().filter(
				n-> n.getPositions().contains(pos) && !n.getPositions().get(0).equals(pos) && !n.getPositions().get(n.getPositions().size()-1).equals(pos))
				.findFirst();
		return graph.isPresent() ? graph.get() : null;
	}
	
}