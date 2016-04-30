package game_engine;

import java.util.List;

import auth_environment.IAuthEnvironment;
import game_engine.AI.AIHandler;
import game_engine.AI.AISearcher;
import game_engine.AI.AISimulator;
import game_engine.controllers.AIController;
import game_engine.controllers.EnemyController;
import game_engine.controllers.LevelController;
import game_engine.controllers.UnitController;
import game_engine.interfaces.ILevelDisplayer;
import game_engine.properties.Position;


/**
 * This interface is the external API for the game player module. It facilitates
 * user actions to the game engine. Throws exception when wrong files are passed
 * to setUpEngine or towers cannot be found/modified. Throws exception when games
 * cannot be saved/loaded correctly.
 * 
 * @author ownzandy
 *
 */

public interface GameEngineInterface {

	List<String> saveGame ();

	void update ();

	void setUpEngine (IAuthEnvironment data);

	public UnitController getUnitController ();

	public LevelController getLevelController ();
	
	public EnemyController getEnemyController ();
	
	public AIController getAIController ();

	public int getNextWaveTimer ();

	public void setCursorPosition (double x, double y);

	public Position getCursorPosition ();

	ILevelDisplayer getLevelDisplay();

}