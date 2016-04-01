package auth_environment.view;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class is responsible for loading and saving Game Data.
 */

public interface SaveLoadDisplay {

    public void load(); // loads existing Game Data into the Auth Environment for further editing

    public void save(); // saves current state to Game Data

}
