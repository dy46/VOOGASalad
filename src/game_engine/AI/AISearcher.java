package game_engine.AI;

import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * AISearcher interface allows for Artificial Intelligence search problems on nodes (end positions of Branches).
 * AI Search problems operate on Search Tuples which hold search-specific data.
 * 
 * @author adamtache
 *
 */

public interface AISearcher {

	public List<Branch> getPath(Position current);
	
	public List<Branch> getPath(Position current, List<Position> visibleNodes);
	
	public boolean isValidSearch(SearchTuple myTuple);
	
}