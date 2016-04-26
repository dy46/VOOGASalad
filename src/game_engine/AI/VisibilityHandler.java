package game_engine.AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.EncapsulationChecker;
import game_engine.properties.Position;


/**
 * This class is a visibility handler that allows access to visibility graphs and visibility
 * utilities of valid search problems.
 * A visibility graph is represented by branches valid after the consideration of obstacles.
 * 
 * @author adamtache
 *
 */

public class VisibilityHandler {

	private GameEngineInterface myEngine;

	public VisibilityHandler (GameEngineInterface engine) {
		myEngine = engine;
	}

	public List<Branch> getVisibilityBranches () {
		return getVisibilityBranches(myEngine.getUnitController().getUnitType("Tower"));
	}

	public List<Branch> getVisibilityBranches (Unit obstacle) {
		List<Unit> obstacles = getUnitCopyList(myEngine.getUnitController().getUnitType("Tower"));
		obstacles.add(obstacle.copyShallowUnit());
		return getVisibilityBranches(obstacles);
	}

	public boolean isPositionVisible (List<Branch> visibilityBranches, Position spawn) {
		for (Branch v : visibilityBranches) {
			for (Position p : v.getPositions()) {
				if (p.equals(spawn)) {
					return true;
				}
			}
		}
		return false;
	}

	private List<Branch> getVisibilityBranches (List<Unit> obstacles) {
		return getFilteredVisibilityBranches(getBranchCopyList(getBranchesToFilter(obstacles)));
	}

	private List<Branch> getFilteredVisibilityBranches (List<Branch> branchesToFilter) {
		List<Branch> visibilityBranches = getBranchCopyList(myEngine.getBranches());
		for(Branch visible : visibilityBranches){
			fixNeighbors(visible, branchesToFilter);
		}
		for(Branch branchToFilter : branchesToFilter){
			removeBranch(visibilityBranches, branchToFilter);
		}
		return visibilityBranches;
	}

	private void removeBranch(List<Branch> branches, Branch toRemove){
		int removalIndex = -1;
		for(int x=0; x<branches.size(); x++){
			if(branches.get(x).equals(toRemove)){
				removalIndex = x;
				break;
			}
		}
		if(removalIndex != -1)
			branches.remove(removalIndex);
	}

	private void fixNeighbors(Branch branch, List<Branch> branchesToFilter) {
		for (Branch filteredBranch : branchesToFilter) {
			branch.removeNeighbor(filteredBranch);
		}
	}

	private List<Branch> getBranchesToFilter (List<Unit> obstacles) {
		Set<Branch> removalList = new HashSet<>();
		List<Branch> copyBranches = getBranchCopyList(myEngine.getBranches());
		List<Unit> copyObstacles = getUnitCopyList(obstacles);
		for (Unit o : copyObstacles) {
			for (Branch b : copyBranches) {
				for (Position pos : b.getPositions()) {
					if (EncapsulationChecker.encapsulates(Arrays.asList(pos), o.getProperties()
							.getBounds().getUseableBounds(o.getProperties().getPosition()))) {
						removalList.add(b);
					}
				}
			}
		}
		return new ArrayList<Branch>(removalList);
	}

	private List<Branch> getBranchCopyList(List<Branch> branches){
		return branches.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
	}

	private List<Unit> getUnitCopyList(List<Unit> units){
		return units.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
	}

}