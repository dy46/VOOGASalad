package game_engine.game_elements;

import java.util.ArrayList;
import java.util.Arrays;
import game_engine.properties.Damage;
import game_engine.properties.Velocity;


public class Enemy extends LiveableUnit {

    public Enemy (String name) {
        super(name);
//        setID(getWorkspace().getIDFactory().createID(this));
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
    public void takeDamage (double damage) {
        Damage myDamage = getProperties().getDamage();
        myDamage.setDamage(myDamage.getDamage() - damage);
    }

    public String toFile () {
        return "Enemy " + getID();
    }

    public void update () {
        super.update(this);
        addMoveAffector(1);
    }
        
        
    public void addMoveAffector(int TTL) {
        Velocity v = getProperties().getVelocity();
        double xOffset = v.getSpeed()*Math.cos(v.getDirection());
        double yOffset = v.getSpeed()*Math.sin(v.getDirection());
        addAffector(getAffectorFactory().constructAffector("Position",
                                                           "Move", 
                                                           new ArrayList<Double>(Arrays.asList(xOffset, yOffset)),
                                                           TTL));
    }
}
