package game_engine.AI;

import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public interface AISearcher {

	public List<Branch> getPath(Position current);
	
	public List<Branch> getPath(Position current, List<Position> visibleNodes);
	
	public SearchTuple getTuple(List<Position> sources, List<Position> visibleNodes);
	
	public boolean isValidSearch(SearchTuple myTuple);
	
}