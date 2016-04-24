package auth_environment.paths;

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

public class VisibilityGraph {

	private EncapsulationChecker myEncapsulator;
	private GameEngineInterface myEngine;

	public VisibilityGraph(Unit u, GameEngineInterface engine){
		myEncapsulator = new EncapsulationChecker();
		myEngine = engine;
	}

	public List<Branch> getVisibilityBranches(){
		List<Branch> branchesToFilter = getBranchesToFilter();
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
		System.out.println("TO FILTER: " + copyBranchesToFilter.get(0).getFirstPosition()+" "+copyBranchesToFilter.get(0).getLastPosition());
		for(int y=0; y<copyBranchesToFilter.size(); y++){
			for(int x=0; x<branches.size(); x++){
				if(copyBranchesToFilter.get(y).equals(branches.get(x))){
					branches.remove(x);
					x--;
				}
			}	
		}
		return branches;
	}

	public List<Branch> getBranchesToFilter(){
		Set<Branch> removalList = new HashSet<>();
		List<Unit> obstacles = myEngine.getTowers();
		System.out.println("OBSTACLE: " + obstacles.get(obstacles.size()-1).getProperties().getPosition());
		for(Unit obstacle : obstacles){
			for(Branch b : myEngine.getBranches()){
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