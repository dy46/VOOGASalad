package game_player.interfaces;

import exceptions.WompException;
import javafx.scene.control.Tab;


/**
 * Interface for any kind of tab required for the player.
 * 
 * @author David
 *
 */
public interface IPlayerTab {

    /**
     * Creates Tab object that will be placed into TabPane.
     * 
     * @return Tab
     * @throws WompException 
     */
    Tab getTab () throws WompException;
}
