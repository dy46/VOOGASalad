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
	private List<Affector> affectorsToApply;
	private int TTL;
	private boolean setToDeath;
	private boolean hasCollided;
	private int deathDelay;
	private int elapsedTime;
	private int numFrames;
	private List<Double> numberList;

	public Unit (String name, List<Affector> affectors, int numFrames) {
		super(name);
		initialize();
		myProperties = new UnitProperties();
		elapsedTime = 0;
		this.numFrames = numFrames;
		addAffectors(affectors);
	}
	
	public Unit (String name, int numFrames) {
		super(name);
		myProperties = new UnitProperties();
		initialize();
		elapsedTime = 0;
		this.numFrames = numFrames;
	}

	public Unit (String ID, UnitProperties properties) {
		super(ID);
		initialize();
		this.myProperties = properties;
		this.setToDeath = false;
	}

	public Unit(String name){
		super(name);
		initialize();
		this.myProperties = new UnitProperties();
		elapsedTime = 0;
	}

	private void initialize () {
		myAffectors = new ArrayList<>();
		this.setHasCollided(false);
	}

	// returns false if died
	public void update () {
		if(isVisible()) {
			elapsedTime++;
			myAffectors.removeIf(a -> a.getTTL() == a.getElapsedTime());
			myAffectors.forEach(a -> a.apply(this));
//			if(!(this instanceof Terrain))
//				System.out.println("Enemy health: " + myProperties.getHealth().getValue());
		}
		if (!isAlive()) {
			setElapsedTimeToDeath();
			setToDeath = true;
		}
	}

	public boolean isAlive () {
		if(myProperties.getHealth() == null)
			return false;
		return myProperties.getHealth().getValue() > 0;
	}

	public void setInvisible(){
		this.setElapsedTime(this.getTTL());
	}

	public void addAffectors (List<Affector> affectors) {
		myAffectors.addAll(affectors);
	}

	public void addAffector(Affector affector){
		myAffectors.add(affector);
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

	public List<Affector> getAffectorsToApply() {
		return affectorsToApply;
	}

	public void setAffectorsToApply(List<Affector> affectorsToApply) {
		this.affectorsToApply = affectorsToApply;
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

	public double getSellPrice(){
		return getProperties().getPrice().getValue();
	}

//	public void sell(Unit unit){
//		getWorkspace().addBalance(getSellPrice());
//		getWorkspace().remove(unit);
//	}
	
	public List<Double> getNumberList () {
        return numberList;
    }

    public void setNumberList (List<Double> numberList) {
        this.numberList = numberList;
    }

}