package game_engine.place_validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import auth_environment.paths.VisibilityGraph;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class TowerPlaceValidation extends PlaceValidation{

	public boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY) {
		VisibilityGraph myVisibility = new VisibilityGraph(unit, gameEngine);
		gameEngine.addTower(unit.getName(), posX, posY);
		System.out.println("NORMAL BRANCHES: " + gameEngine.getBranches());
		List<Branch> visibilityBranches = myVisibility.getVisibilityBranches();
		System.out.println("VISIBILITY BRANCHES: " + visibilityBranches);
		gameEngine.removeTower(unit);
		gameEngine.sellUnit(unit);
		for(Position goal : gameEngine.getCurrentLevel().getGoals()){
			for(Position spawn : gameEngine.getCurrentLevel().getSpawns()){
				if(!DFSPossible(gameEngine, visibilityBranches, spawn, goal)){
					return false;
				}
			}
		}
		return true;
	}

	private boolean DFSPossible(GameEngineInterface gameEngine, List<Branch> visibilityBranches, Position spawn, Position goal){
		Branch start = gameEngine.findBranchForSpawn(spawn);
		Stack<Branch> frontier = new Stack<>();
		List<Branch> visited = new ArrayList<>();
		frontier.push(start);
		while(!frontier.isEmpty()){
			Branch curr = frontier.peek();
			List<Branch> neighbors = curr.getNeighbors();
			Branch nextBranch = null;
			for(Branch b : neighbors){
				if(b.getPositions().contains(goal)){
					return true;
				}
				if(!visited.contains(b)){
					nextBranch = b;
				}
			}
			if(nextBranch != null){
				visited.add(nextBranch);
				frontier.push(nextBranch);
			}
			else{
				frontier.pop();
			}
		}
		return false;
	}

}