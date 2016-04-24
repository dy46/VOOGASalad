package game_engine.place_validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import auth_environment.paths.VisibilityGraph;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;

public class TowerPlaceValidation extends PlaceValidation{

	public boolean validate(GameEngineInterface gameEngine, Unit unit, double posX, double posY) {
		Unit copy = unit.copyShallowUnit();
		shiftBounds(copy, posX, posY);
		VisibilityGraph myVisibility = new VisibilityGraph(gameEngine);
		List<Branch> visibilityBranches = myVisibility.getSimulatedPlacementBranches(copy);
		for(Position goal : gameEngine.getCurrentLevel().getGoals()){
			for(Position spawn : gameEngine.getCurrentLevel().getSpawns()){
				if(!BFSPossible(gameEngine, visibilityBranches, spawn, goal)){
					System.out.println("NOT VALIDATED");
					return false;
				}
			}
		}
		System.out.println("VALIDATED");
		return true;
	}

	private boolean BFSPossible(GameEngineInterface gameEngine, List<Branch> visibilityBranches, Position spawn, Position goal){
		Branch start = gameEngine.findBranchForSpawn(spawn);
		Branch copyStart = start.copyBranch();
		Queue<Branch> queue = new LinkedList<>();
		List<Branch> visited = new ArrayList<>();
		queue.add(copyStart);
		while(!queue.isEmpty()){
			Branch branch = (Branch) queue.remove();
			Branch child = null;
			while((child = getUnvisitedChildNode(branch, visited, visibilityBranches)) != null){
				visited.add(child);
				queue.add(child);
			}
		}
		for(Branch b : visited){
			if(b.getPositions().contains(goal)){
				return true;
			}
		}
		return false;
	}

	private Branch getUnvisitedChildNode(Branch branch, List<Branch> visited, List<Branch> visible) {
		List<Branch> neighbors = branch.getNeighbors();
		List<Branch> visibleNeighbors = new ArrayList<>();
		for(Branch b : neighbors){
			for(Branch v : visible){
				if(!visited.contains(b)){
					if(b.equals(v)){
						visibleNeighbors.add(b);
					}
				}
			}
		}
		if(visibleNeighbors.size() == 0){
			return null;
		}
		return visibleNeighbors.get(0);
	}

	private void shiftBounds(Unit u, double x, double y){
		double newX = u.getProperties().getPosition().getX() - 200 + x;
		double newY = u.getProperties().getPosition().getY() - 300 + y;
		Position newPos = new Position(newX, newY);
		u.getProperties().setPosition(newPos);
		Position topLeftPos = new Position(newX - 50, newY - 50);
		Position topRightPos = new Position(newX + 50, newY - 50);
		Position botLeftPos = new Position(newX - 50, newY + 50);
		Position botRightPos = new Position(newX + 50, newY + 50);
		u.getProperties().setBounds(Arrays.asList(topLeftPos, topRightPos, botLeftPos, botRightPos));
	}

}