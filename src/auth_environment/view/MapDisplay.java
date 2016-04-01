package auth_environment.view;

import game_engine.game_elements.GameElement;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Xander
 *
 * This class is for the main Map Display showing the current Map.
 * The Developer should be able to place Game Elements (as long as they are placeable).
 */

public interface MapDisplay {

    public void displayElement(GameElement element);
    
}
