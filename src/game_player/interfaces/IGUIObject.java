// This entire file is part of my masterpiece.
// David Yang

/**
 * I add this class to my masterpiece because it is the main interface
 * for the front end of the game player. This interface is the internal
 * API for the game player and is required to add any new elements or
 * features to the game player. 
 */

package game_player.interfaces;

import javafx.scene.Node;


/**
 * Interface for creating and updating GUI elements.
 * 
 * @author ownzandy
 *
 */
public interface IGUIObject {

    // creates GUI object
    Node createNode ();

    // updates GUI object
    void updateNode ();
}
