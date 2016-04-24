package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathNode {

	private List<Branch> myBranches;

	public PathNode(){
		myBranches = new ArrayList<>();
	}

	public PathNode(Branch branch) {
		myBranches = new ArrayList<>();
		addBranch(branch);
	}

	public PathNode(List<Branch> branches) {
		myBranches = new ArrayList<>();
		myBranches.addAll(branches);
	}

	public void addBranch(Branch branch){
		myBranches.add(branch);
	}

	public List<Branch> getBranchNodes(Branch branch){
		List<Branch> nodes = branch.getNeighbors().stream().filter(n -> getBranchNodes(n) != null).collect(Collectors.toList());
		nodes.add(branch);
		return nodes;
	}

	public List<Branch> copyBranches(){
		return myBranches.stream().map(p -> new Branch(p.getPositions(), p.getNeighbors())).collect(Collectors.toList());
	}

	public List<Branch> getBranchesByEdgePosition(Position pos){
		return myBranches.stream().filter(
				n -> n.getPositions().size() > 0 && (n.getPositions().get(0).roughlyEquals(pos) || n.getPositions().get(n.getPositions().size()-1).roughlyEquals(pos)))
				.collect(Collectors.toList());
	}

	public List<Branch> getBranchesByMidPosition(Position pos){
		return myBranches.stream().filter(
				n-> n.getPositions().contains(pos) && !n.getPositions().get(0).roughlyEquals(pos) && !n.getPositions().get(n.getPositions().size()-1).roughlyEquals(pos)).collect(Collectors.toList());
	}

	public List<Branch> getBranches(){
		return myBranches;
	}

	public void removeBranch(Branch b) {
		if(myBranches.contains(b)){
			myBranches.remove(b);
		}
	}

}