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

	public PathGraph getVisibilityGraph(PathGraph graph, Unit u){
		List<Unit> obstacles = u.getObstacles(); // TODO: replace with file check
		List<Branch> branchesToFilter = getBranchesToFilter(graph, obstacles);
		List<Branch> branches = graph.copyBranches();
		branches.removeAll(branchesToFilter);
		return new PathGraph(branches);
	}

	public List<Branch> getBranchesToFilter(PathGraph path, List<Unit> obstacles){
		Set<Branch> removalList = new HashSet<>();
		for(Unit obstacle : obstacles){
			for(Branch b : path.getBranches()){
				for(Position pos : b.getPositions()){
					if(myEncapsulator.encapsulatesBounds(Arrays.asList(pos), obstacle.getProperties().getBounds())){
						removalList.add(b);
					}
				}
			}
		}
		return new ArrayList<Branch>(removalList);
	}

}