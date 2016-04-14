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

    /*
     * updates the Enemy's health when an Affector is applied to it.
     */

    public String toFile () {
        return "Enemy " + getID();
    }
    
    public void update() {
        super.update();
        System.out.println(this.getProperties().getHealth().getValue());
    }

}