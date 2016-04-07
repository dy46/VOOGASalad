package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.properties.UnitProperties;


/**
 * This class is the superclass for game units and implements GameElement.
 * It represents any physical game unit and holds its ID, UnitProperties, and list of current
 * Affectors to be applied.
 * 
 * @author adamtache
 *
 */

public abstract class Unit extends GameElement {

    private UnitProperties myProperties;
    private List<Affector> myAffectors;
    private int TTL;
    private int elapsedTime;

    public Unit (String name, List<Affector> affectors) {
        super(name);
        initialize();
        myProperties = new UnitProperties();
        elapsedTime = 0;
        addAffectors(affectors);
    }

    public Unit (String ID, UnitProperties properties) {
        super(ID);
        initialize();
        this.myProperties = properties;
        initialize();
    }

    private void initialize () {
        myAffectors = new ArrayList<>();
    }

    public void update () {
        elapsedTime++;
        myAffectors.removeIf(a -> a.getTTL() == a.getElapsedTime());
        myAffectors.forEach(a -> a.apply(myProperties));
    }

    public void addAffectors (List<Affector> affectors) {
        myAffectors.addAll(affectors);
    }

    public UnitProperties getProperties () {
        return myProperties;
    }

    public void setProperties (UnitProperties properties) {
        this.myProperties = properties;
    }

    public List<Affector> getAffectors () {
        return myAffectors;
    }

    public void setAffectors (List<Affector> affectors) {
        this.myAffectors = affectors;
    }

    public int getTTL () {
        return TTL;
    }

    public int getElapsedTime () {
        return elapsedTime;
    }

    public void setTTL (int TTL) {
        this.TTL = TTL;
    }
    
    public void incrementElapsedTime(int i) {
        this.elapsedTime += 1;
    }

}
