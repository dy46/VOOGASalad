package game_engine.game_elements;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.properties.UnitProperties;


/*
 * External API interface that will be available to the authoring environment for extension and
 * creation
 * of XML files for use in games. API specifies some basic functionality of towers and which methods
 * need to
 * be implemented for subclasses created in the authoring environment.
 */
public class Tower extends SellableUnit {

    private List<Unit> allProjectiles;
    private List<Projectile> myProjectiles;
    
    public Tower (String name, List<Affector> affectors, int numFrames) {
        super(name, affectors, numFrames);
        // setID(getWorkspace().getIDFactory().createID(this));
    }
    

    public Tower (String name, List<Affector> affectors, List<Unit> allProjectiles, 
                  List<Projectile> myProjectiles, int numFrames) {
        super(name, affectors, numFrames);
        this.allProjectiles = allProjectiles;
        this.myProjectiles = myProjectiles;
        // setID(getWorkspace().getIDFactory().createID(this));
    }

    /*
     * method for activating the tower attack (subclasses implement different types of attack
     * types).
     */
    public void fire () {
        List<Projectile> newProjectiles = myProjectiles.stream()
                                                       .filter(p -> p.getElapsedTime() % p.getFireRate() == 0)
                                                       .map(p -> p.copyProjectile()).collect(Collectors.toList());
        newProjectiles.forEach(p -> {
                                  allProjectiles.add(p);
                                  p.getProperties().setPosition(getProperties().getPosition().getX(), 
                                                                getProperties().getPosition().getY());
                               });      
    }
    
    public void update () {
        super.update();
        myProjectiles.stream().forEach(p -> p.incrementElapsedTime(1));
    }
    
    /*
     * sells the tower, and removes it from the list of active towers
     */
    public void sell () {
        super.sell(this);
    }

    /*
     * changes the UnitProperties of the tower to reflect an upgrade (higher damage, better armor,
     * etc).
     */
    public void upgrade (UnitProperties newProperties) {
        setProperties(newProperties);
    }

}
