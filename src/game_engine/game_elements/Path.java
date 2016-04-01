package game_engine.game_elements;
/*
* Internal API that will be used in order to represent paths 
* for enemy movements.
*/
public interface Path {
	/*
	* Gets the next position (point) in the path.
	* This will be used in order to determine which direction 
	* an Enemy needs to move in next.
	*
	* @return	The next Position in the list of Positions that represent the path being taken.
	*/
	public Position getNextPosition();
}
