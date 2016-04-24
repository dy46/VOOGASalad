package game_engine;

import java.util.List;
import auth_environment.IAuthEnvironment;
import auth_environment.paths.PathNode;
import exceptions.WompException;
import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.factories.FunctionFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.games.Timer;
import game_engine.physics.CollisionDetector;


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

    // tells the engine to save the game and returns the list of fileNames that can be retrieved
    List<String> saveGame ();

    // tells engine to play a specified level
    void playLevel (int levelNumber);

    // tells engine to play a specified wave
    void playWave (int waveNumber);

    // tells engine to update elements a single time unit
    void update ();

    // asks engine about the game status
    String getGameStatus ();

    // tells engine to add tower to its active tower list given a tower index
    boolean addTower (String name, double x, double y);

    // tells engine to modify tower given an activeTower index and list of changes
    void modifyTower (int activeTowerIndex, UnitProperties newProperties);

    void setUpEngine(IAuthEnvironment data);

    public List<Unit> getEnemies ();

    public List<Unit> getTowers ();

    public List<Unit> getTerrains ();

    public List<Level> getLevels ();

    public int getLives ();

    public List<Unit> getProjectiles ();

    public void clearProjectiles ();

    public List<Unit> getTowerTypes ();

    public List<Branch> getBranches ();

    public boolean isPaused ();

    public void setPaused ();

    public boolean isGameOver();

    public Timer getTimer ();

    public Level getCurrentLevel ();

    public void decrementLives (int lives);
    
    public List<Position> getGoals ();

    public int getNextWaveTimer ();

	public List<Unit> getAllUnits();

    public FunctionFactory getFunctionFactory ();

    public double getScore ();

    public void setScore (double score);

    public List<PathNode> getPaths ();

    public List<Branch> getGridBranches ();
    
    public List<Affector> getUpgrades(Unit name);
    
    public void applyUpgrade(Unit name, Affector affector);
    
    public void sellUnit(Unit name);
    
    public void moveUnit(Unit unit, double x, double y);
    
    public void setCursorPosition(double x, double y);
    
    public Position getCursorPosition();
    
    public int getMoney();

}
