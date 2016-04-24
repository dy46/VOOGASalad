package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.physics.EncapsulationChecker;
import game_engine.properties.Position;

public class VisibilityGraphFilter {

	private EncapsulationChecker myEncapsulator;

	public VisibilityGraphFilter(){
		myEncapsulator = new EncapsulationChecker();
	}

	public void filterObstacles(PathGraph graph, List<Unit> obstacles){
		List<Branch> branchesToFilter = getBranchesToFilter(graph, obstacles);
		for(Branch b : branchesToFilter){
			graph.removeBranch(b);
		}
	}

	public void filterObstacles(PathNode path, List<Unit> obstacles){
		List<Branch> branchesToFilter = getBranchesToFilter(path, obstacles);
		for(Branch b : branchesToFilter){
			path.removeBranch(b);
		}
	}

	public List<Branch> getBranchesToFilter(PathGraph graph, List<Unit> obstacles){
		Set<Branch> removalList = new HashSet<>();
		for(PathNode path : graph.getPaths()){
			List<Branch> branches = getBranchesToFilter(path, obstacles);
			removalList.addAll(branches);
		}
		return new ArrayList<Branch>(removalList);
	}

	public List<Branch> getBranchesToFilter(PathNode path, List<Unit> obstacles){
		Set<Branch> removalList = new HashSet<>();
		for(Unit obstacle : obstacles){
			for(Branch b : path.getBranches()){
				for(Position pos : b.getAllPositions()){
					if(myEncapsulator.encapsulatesBounds(Arrays.asList(pos), obstacle.getProperties().getBounds())){
						removalList.add(b);
					}
				}
			}
		}
		return new ArrayList<Branch>(removalList);
	}

}