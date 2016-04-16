package game_engine;

import java.util.ArrayList;
import java.util.Collection;

import game_engine.games.TD.TDGame;

/**
 * This class represents the womp-wide controller for all instances of specific game engine implementations.
 * Holds data for all instances of current games for switching between them and other actions.
 * @author adamtache
 *
 */

public class EngineController {

	Collection<TDGame> myActiveEngines;
	TDGame myActiveWorkspace;
	
	public void initialize(){
		myActiveEngines = new ArrayList<>();
		myActiveWorkspace = new TDGame();
		myActiveEngines.add(myActiveWorkspace);
	}
	
}