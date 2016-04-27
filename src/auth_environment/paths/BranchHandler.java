package auth_environment.paths;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class BranchHandler {

	public void configureBranch(Branch newBranch, PathGraph myPath){
		if(!myPath.getBranches().contains(newBranch)){
			Position startPos = newBranch.getFirstPosition();
			Position endPos = newBranch.getLastPosition();
			configureBranchesWithEdgePos(newBranch, myPath, startPos);
			if(startPos != null && endPos !=null && !startPos.equals(endPos)){
				configureBranchesWithEdgePos(newBranch, myPath, endPos);
			}
			configureMidBranchSplits(newBranch, myPath, startPos);
			if(startPos != null && endPos !=null && !startPos.equals(endPos)){
				configureMidBranchSplits(newBranch, myPath, endPos);
			}
		}
	}

	private void configureBranchesWithEdgePos(Branch newBranch, PathGraph myPath, Position pos){
		List<Branch> branchesAtPos = myPath.getBranchesByEdgePosition(pos);
		List<Branch> branchesChecked = new ArrayList<>();
		for(Branch branch : branchesAtPos){
			if(!branchesChecked.contains(branch)){
				branchesChecked.add(branch);
				if(!branch.equals(newBranch)){
					newBranch.addNeighbor(branch);
					branch.addNeighbor(newBranch);
				}
			}
		}
		if(!myPath.getBranches().contains(newBranch))
			myPath.addBranch(newBranch);
	}

	private void configureMidBranchSplits(Branch newBranch, PathGraph myPath, Position pos){
		List<Branch> branchesToSplit = myPath.getBranchesByMidPosition(pos);
		branchesToSplit.stream().filter(b -> b.equals(newBranch));
		for(Branch b : branchesToSplit){
			List<Position> cutoffPositions = b.cutoffByPosition(pos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			Branch newSplitBranch = new Branch();
			newSplitBranch.addPositions(cutoffPositions);
			newSplitBranch.addNeighbor(b);
			newSplitBranch.addNeighbor(newBranch);
			b.addNeighbor(newSplitBranch);
			b.addNeighbor(newBranch);
			List<Branch> cutoffConnectedBranches = myPath.getBranchesByEdgePosition(lastCutoff);
			for(Branch br : cutoffConnectedBranches){
				newSplitBranch.addNeighbors(b.removeNeighbors(br.getNeighbors()));
			}
			newBranch.addNeighbor(b);
			newBranch.addNeighbor(newSplitBranch);
			myPath.addBranch(newSplitBranch);
		}
	}

}