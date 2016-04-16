package game_engine;

import java.util.ArrayList;
import java.util.Collection;

import game_engine.genres.TD.TDGame;

/**
 * This class represents the womp-wide controller for all instances of specific game engine implementations.
 * Holds data for all instances of current games for switching between them and other actions.
 * @author adamtache
 *
 */

public class EngineController {

	private Collection<TDGame> myActiveGames;
	private TDGame TD;
	
	public void initialize(){
		myActiveGames = new ArrayList<>();
		TD = new TDGame();
		myActiveGames.add(TD);
	}
	
}