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

	public List<Branch> getVisibilityBranches(Unit u){
		List<Branch> branchesToFilter = getBranchesToFilter();
		List<Branch> branches = myEngine.getBranches().stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		branches.removeAll(branchesToFilter);
		return branches;
	}

	public List<Branch> getBranchesToFilter(){
		Set<Branch> removalList = new HashSet<>();
		List<Unit> obstacles = myEngine.getTowers();
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