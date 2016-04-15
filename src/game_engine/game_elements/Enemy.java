package game_engine.game_elements;

import java.util.List;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;

public class Enemy extends Unit {

    public Enemy (String name, List<AffectorTimeline> timelines, int numFrames) {
        super(name, timelines, numFrames);
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