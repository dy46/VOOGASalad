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

	public List<Branch> getSimulatedPlacementBranches(Unit obstacle){
		System.out.println("OBSTACLE: " + obstacle.getProperties().getPosition());
		List<Branch> branchesToFilter = getBranchesToFilter(obstacle);
		List<Branch> copyBranchesToFilter = branchesToFilter.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		PathGraph pg = new PathGraph(myEngine.getBranches());
		List<Branch> branches = pg.copyGraph().getBranches();
		List<Branch> copyBranches = branches.stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		if(copyBranchesToFilter.size() > 0 ){
			System.out.println("TO FILTER: " + copyBranchesToFilter.get(0).getFirstPosition()+" "+copyBranchesToFilter.get(0).getLastPosition());
		}
		for(int y=0; y<copyBranchesToFilter.size(); y++){
			for(int x=0; x<copyBranches.size(); x++){
				if(copyBranchesToFilter.get(y).equals(copyBranches.get(x))){
					Branch removed = copyBranches.remove(x);
					x--;
					for(Branch b : copyBranches){
						for(int z=0; z < b.getNeighbors().size(); z++){
							if(b.getNeighbors().get(z).equals(removed)){
								b.getNeighbors().remove(z);
								z--;
							}
						}
					}
				}
			}	
		}
		return copyBranches;
	}

	private List<Branch> getBranchesToFilter(Unit obstacle){
		Set<Branch> removalList = new HashSet<>();
		List<Branch> copyBranches =  myEngine.getBranches().stream().map(b -> b.copyBranch()).collect(Collectors.toList());
		List<Unit> obstacleList = myEngine.getTowers();
		List<Unit> copyObstacleList = obstacleList.stream().map(o -> o.copyShallowUnit()).collect(Collectors.toList());
		copyObstacleList.add(obstacle);
		for(Unit o : copyObstacleList){
			for(Branch b : copyBranches){
				for(Position pos : b.getPositions()){
					System.out.println("POS: " + pos + " BOUNDS: " + o.getProperties().getBounds());
					if(myEncapsulator.encapsulatesBounds(Arrays.asList(pos), o.getProperties().getBounds())){
						removalList.add(b);
						System.out.println("ENCAPSULATED");
					}
				}
			}
		}
		return new ArrayList<Branch>(removalList);
	}

}