package game_engine.game_elements;

import java.util.List;
import game_engine.affectors.Affector;

public class Enemy extends Unit {

    public Enemy (String name, List<Affector> affectors, int numFrames) {
        super(name, affectors, numFrames);
        //setID(getWorkspace().getIDFactory().createID(this));
    }

    /*
     * the Enemy fires a projectile at a target, which subsequently applies
     * an Affector on it.
     *
     */
    public void fire () {

    }
    
    public void update() {
        super.update();
    }

}