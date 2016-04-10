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
    private boolean setToDeath;
    private boolean hasCollided;
    private int deathDelay;
    private int elapsedTime;
    private int numFrames;

    public Unit (String name, List<Affector> affectors, int numFrames) {
        super(name);
        initialize();
        myProperties = new UnitProperties();
        elapsedTime = 0;
        this.numFrames = numFrames;
        addAffectors(affectors);
    }

    public Unit (String ID, UnitProperties properties) {
        super(ID);
        initialize();
        this.myProperties = properties;
        this.setToDeath = false;
    }

    private void initialize () {
        myAffectors = new ArrayList<>();
        this.setHasCollided(false);
    }

    public void update () {
        elapsedTime++;
        myAffectors.removeIf(a -> a.getTTL() == a.getElapsedTime());
        myAffectors.forEach(a -> a.apply(myProperties));
    }
    public void setInvisible(){
    	this.setElapsedTime(this.getTTL());
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
    
    public void setDeathDelay(int deathDelay) {
        this.deathDelay = deathDelay;
    }
    
    public int getDeathDelay() {
        return deathDelay;
    }
    
    public void incrementTTL(int increment) {
        this.TTL += increment;
    }
    
    public void setElapsedTimeToDeath() {
        if(!setToDeath) {
            this.setElapsedTime(this.getTTL() - deathDelay);
            this.getProperties().getState().changeState(5);
            setToDeath = true;
        }
    }
    
    public boolean isVisible() {
        return this.getTTL() >= this.getElapsedTime();
    }
    
    public void incrementElapsedTime(int i) {
        this.elapsedTime += 1;
    }
    
    public void setElapsedTime(int newTime){
    	elapsedTime = newTime;
    }

    public boolean hasCollided () {
        return hasCollided;
    }

    public void setHasCollided (boolean hasCollided) {
        this.hasCollided = hasCollided;
    }
    
    public int getNumFrames() {
        return numFrames;
    }

}
