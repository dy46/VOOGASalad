package game_data;

import java.io.FileNotFoundException;

import game_engine.games.IPlayerEngineInterface;

public interface IGameDataHandler {
	
	/**
	 *  Saves the GameEnvironment to a folder
	 * @return String representing the filepath to the folder where the game data is stored.
	 */
	public String saveGame(IPlayerEngineInterface g);
	
	/**
	 * @param Path to folder where the game data is stored
	 * @return The game saved the the folder
	 * @throws FileNotFoundException if filePath is invalid or file format is not valid.
	 */
	public IPlayerEngineInterface loadGame(String filePath) throws FileNotFoundException;
	
}
