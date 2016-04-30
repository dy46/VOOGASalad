package game_engine.AI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import game_engine.GameEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.handlers.VisibilityHandler;
import game_engine.properties.Position;

/**
 * This class is a utility Artificial Intelligence searcher that allows for search problems.
 * A search problem is composed of a list of branches, which compose a "path graph".
 * 
 * @author adamtache
 *
 */

public class AISearcher {

	private GameEngineInterface myEngine;
	private VisibilityHandler myVisibility;

	public AISearcher(GameEngineInterface engine){
		this.myEngine = engine;
		this.myVisibility = new VisibilityHandler(engine);
	}

	public List<Branch> getPath(Position current){
		List<Position> visibleNodes = myVisibility.getVisibleNodes();
		return getPath(current, visibleNodes);
	}

	public List<Branch> getPath(Position current, List<Position> visibleNodes){
		BFSTuple tuple = getBFSTuple(getGoals(), visibleNodes);
		List<Branch> path = tuple.getPathTo(current, null, current);
		if(path != null){
			return path;
		}
		return null;
	}

	public BFSTuple getBFSTuple(List<Position> sources, List<Position> visibleNodes){
		Queue<Position> queue = new LinkedList<>();
		HashMap<Position, Integer> distances = new HashMap<>();
		HashMap<Position, Position> edges = new HashMap<>();
		HashSet<Position> visited = new HashSet<>();
		for(Position source : sources){
			visited.add(source);
			distances.put(source, 0);
			queue.add(source);
		}
		while(!queue.isEmpty()){
			Position next = queue.poll();
			for(Position adjacent : myVisibility.getVisibleNeighbors(next, getLevelBranches(), visibleNodes)){
				if(!visited.contains(adjacent)){
					edges.put(adjacent, next);
					distances.put(adjacent, distances.get(next) + 1);
					visited.add(adjacent);
					queue.add(adjacent);
				}
			}
		}
		return new BFSTuple(getLevelBranches(), visited, edges, distances);
	}

	public boolean isValidSearch(BFSTuple myBFS){
		List<Position> spawns = myEngine.getLevelController().getCurrentLevel().getSpawns();
		for(Position spawn : spawns){
			if(!myBFS.hasPathTo(spawn)){
				return false;
			}
		}
		return true;
	}

	private List<Position> getGoals(){
		return myEngine.getLevelController().getCurrentLevel().getGoals();
	}
	
	private List<Branch> getLevelBranches(){
		return myEngine.getLevelController().getCurrentBranches();
	}

}