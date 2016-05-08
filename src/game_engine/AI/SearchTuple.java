/**
 * This entire file is part of my masterpiece.
 * Adam Tache
 * 
 * This was the second part of my effort to refactor search problems within the game engine. I created this interface to outline information needed for creating efficient search problems.
 * Search tuples reduce computation as described in the header of AISearcher.
 */

package game_engine.AI;

import java.util.List;

import game_engine.game_elements.Branch;
import game_engine.properties.Position;

/**
 * SearchTuple holds information specific to search problems on search graphs.
 * A search problem is composed of Branches with Positions that Units move on. 
 * 
 * @author adamtache
 *
 */

public interface SearchTuple {
	
    /**
     * Gets a path from the current position on the current Branch to the goal.
     * 
     * @param goal
     * @param currBranch
     * @param currPos
     */
	public List<Branch> getPathTo(Position goal, Branch currBranch, Position currPos);
	
    /**
     * Returns whether or not there's a path to the position in the search.
     * 
     * @param pos
     */
	public boolean hasPathTo(Position pos);
	
}