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

	public VisibilityGraph(GameEngineInterface engine){
		myEncapsulator = new EncapsulationChecker();
		myEngine = engine;
	}

	public List<Branch> getVisibilityBranches(){
		List<Unit> obstacles = myEngine.getTowers();
		List<Unit> obstaclesCopy = obstacles.stream().map(b -> b.copyUnit()).collect(Collectors.toList());
		List<Branch> branchesToFilter = getBranchesToFilter(obstaclesCopy);
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
//		System.out.println("TO FILTER: " + copyBranchesToFilter.get(0).getFirstPosition()+" "+copyBranchesToFilter.get(0).getLastPosition());
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

	public List<Branch> getSimulatedBranches(Unit obstacle){
		List<Unit> obstacles = myEngine.getTowers();
		List<Unit> obstaclesCopy = obstacles.stream().map(b -> b.copyUnit()).collect(Collectors.toList());
		obstaclesCopy.add(obstacle.copyShallowUnit());
//		System.out.println("OBSTACLE: " + obstacle.getProperties().getPosition());
		List<Branch> branchesToFilter = getBranchesToFilter(obstaclesCopy);
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
		List<Branch> copyBranches = branches.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		if(copyBranchesToFilter.size() > 0 ){
//			System.out.println("TO FILTER: " + copyBranchesToFilter.get(0).getFirstPosition()+" "+copyBranchesToFilter.get(0).getLastPosition());
		}
		for(int y=0; y<copyBranchesToFilter.size(); y++){
			for(int x=0; x<copyBranches.size(); x++){
				if(copyBranchesToFilter.get(y).equals(copyBranches.get(x))){
					copyBranches.remove(x);
					x--;
				}
			}	
		}
		return copyBranches;
	}

	private List<Branch> getBranchesToFilter(List<Unit> obstacles){
		Set<Branch> removalList = new HashSet<>();
		if(obstacles.size() > 0 ){
//			System.out.println("OBSTACLE: " + obstacles.get(obstacles.size()-1).getProperties().getPosition());
		}
		List<Branch> copyBranches =  myEngine.getBranches().stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		for(Unit obstacle : obstacles){
			for(Branch b : copyBranches){
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