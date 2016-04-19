package game_engine;

import java.util.List;

import game_data.GameData;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.UnitProperties;

/**
 * Created by BrianLin on 4/19/2016
 * Team member responsible: Brian
 *
 * This interface represents our Game Data at the highest level and corresponds one-to-one with the
 * saved XML file. 
 * 
 * Brian will be responsible for this class' use on the Auth side. 
 * 
 * Note: This interface contains ALL methods in Andy/David's IPlayerEngineInterface in addition to some 
 * AuthEnvironment-only methods. Main purpose of this is to give the Auth Team a quick view of all available
 * methods they can call. 
 */

public interface IEngineWorkspace {
	
	// Updates local variables with GameData's Units and other created objects 
	// TODO: remove GameData references 
	public void setUpEngine(GameData gameData); 
	
	// Loops through and updates those local variables set above
	public void updateElements();
	
	// To be set by Brian's GlobalGameTab. Auth only. 
	public void updateLives();
	
	// Returns remaining waves and lives for David to use on Game Player. 
	public String getGameStatus(); 
	
	// Used by Virginia 
	// TODO: deprecate this? 
	public List<Tower> getTowerTypes();

	// Used by Virginia (and David on Game Player)
	public List<Unit> getProjectiles(); 

	// Used by Virginia (and David on Game Player)
	public List<Unit> getTerrains();
	
	// Used by Virginia (and David on Game Player)
	public List<Unit> getEnemies();

	// Used by Virginia (and David on Game Player)
	// TODO: This is a list of created Towers correct? 
	public List<Unit> getTowers();

	// To be used by Virginia possibly, Auth only
	// TODO: consider deprecating if getEnemies() method doesn't work well 
	public void addEnemy(Enemy enemy); 
	
	// To be set by Austin, Auth only
	public void addLevel(Level level); 

	// To be used by Virginia possibly, Auth only
	// TODO: consider deprecating if we can just use get____() 
	public void remove(Unit unit); 
	
	// TODO: consider deprecating
	public void modifyTower (int activeTowerIndex, UnitProperties newProperties); 

	// Used by Auth to decide what we're editing, Auth only
	public Level getCurrentLevel();

	// Used by GamePlayer? 
	// TODO: who calls this? 
	public double getBalance();

	// Used by Game Player? 
	// TODO: who calls this? 
	public void addBalance(double money); 

	// Used by Austin
	public List<Level> getLevels();

	// Used by Patrick, Xander, Adam
	public List<Branch> getPaths();

	// Used by Brian to display on Global Game tab and by David for Game Player
	public int getLives();
	
	// Used by David? 
	public void clearProjectiles();
	
	// TODO: ask what this even does. Can't really serialize yourself right? 
	public List<String> saveGame();

	// Used by David
	public void playLevel (int levelNumber);

	// Used by David
	public void playWave (int waveNumber);

	// Used by David?
	// TODO: what does this do? 
	public void continueWaves();

	// TODO: consider deprecating 
	public void addTower (String name, double x, double y);

}
