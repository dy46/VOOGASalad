/**
 * This entire file is part of my masterpiece.
 * Adam Tache
 * 
 * I created this interfaces in an effort to refactor searching in the game engine. This interfaces outlines what's needed for a search problem.
 * This interface allows for extensibility of future search problems and provides a way of implementing new types while conserving data.
 * 
 * A big reason why I created the AISearcher and linked SearchTuple interfaces was to reduce computation required for completing search problems.
 * Instead of having to run a search algorithm a bunch of times, these interfaces encourage re-use of data for later since a single search problem can hold a vast amount of information.
 * For example, BFS runs a search from every goal to every other point on the map. If this was done each time a search was required from a spawn to a goal, this would be a waste because this information would already be held in a previous search tuple.
 * 
 */

package game_engine.AI;

import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * AISearcher interface allows for Artificial Intelligence search problems.
 * Search problems operate based on nodes and edges.
 * A Branch is the data structure with end points acting as nodes, and intermediary points acting as edges.
 * AI Search problems operate on Search Tuples which hold search-specific data.
 * 
 * @author adamtache
 *
 */

public interface AISearcher {

    /**
     * Gets a path from the current position to any goal in the Level.
     * 
     * @param current (current Position)
     */
	public List<Branch> getPath(Position current);
	
    /**
     * Gets a path from the current position to any goal in the Level, restrained by the visible nodes.
     * 
     * @param current (current Position)
     * @param visibleNodes (visible search nodes)
     */
	public List<Branch> getPath(Position current, List<Position> visibleNodes);
	
    /**
     * Returns whether or not search is valid.
     * 
     * @param SearchTuple myTuple
     */
	public boolean isValidSearch(SearchTuple myTuple);
	
}