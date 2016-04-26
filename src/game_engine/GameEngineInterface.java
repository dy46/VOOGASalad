package game_engine;

import java.util.List;
import game_engine.game_elements.Branch;
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

    void setUpEngine (TestingGameData data);

    public UnitController getUnitController ();

    public LevelController getLevelController ();

    public List<Branch> getBranches ();

    public int getNextWaveTimer ();

    public void setCursorPosition (double x, double y);

    public Position getCursorPosition ();

    public void updateAIBranches ();

    public List<Branch> getBranchesAtPos (Position pos);

}
