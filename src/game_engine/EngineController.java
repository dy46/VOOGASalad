package game_engine;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the womp-wide controller for all instances of specific game engine implementations.
 * Holds data for all instances of current games for switching between them and other actions.
 * @author adamtache
 *
 */

public class EngineController {

	Collection<EngineWorkspace> myActiveEngines;
	EngineWorkspace myActiveWorkspace;
	
	public void initialize(){
		myActiveEngines = new ArrayList<>();
		myActiveWorkspace = new EngineWorkspace();
		myActiveEngines.add(myActiveWorkspace);
	}
	
}