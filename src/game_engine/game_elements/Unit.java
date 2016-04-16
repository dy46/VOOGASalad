package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.properties.UnitProperties;
import game_engine.timelines.Timeline;


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
	private List<Timeline> myTimelines;
	private List<Timeline> myTimeslinesToApply;
	private int TTL;
	private boolean setToDeath;
	private boolean hasCollided;
	private boolean isEncapsulated;
	private int deathDelay;
	private int elapsedTime;
	private int numFrames;
	private List<Double> numberList;

	public Unit (String name, List<Timeline> timelines, int numFrames) {
		super(name);
		initialize();
		myProperties = new UnitProperties();
		elapsedTime = 0;
		this.numFrames = numFrames;
		addTimelines(timelines);
	}
	
	public Unit (String name, int numFrames) {
		super(name);
		initialize();
		myProperties = new UnitProperties();
		
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
		myTimelines = new ArrayList<>();
		myTimeslinesToApply = new ArrayList<>();
		this.setHasCollided(false);
	}

	public void update () {
		if(isVisible()) {
			elapsedTime++;
			myTimelines.removeIf(t -> t.getAffectors().size() == 0);
			myTimelines.forEach(t -> t.apply(this));
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

	public UnitProperties getProperties () {
		return myProperties;
	}

	public void setProperties (UnitProperties properties) {
		this.myProperties = properties;
	}

	public List<Timeline> getTimelines () {
		return myTimelines;
	}

	public void setTimelines (List<Timeline> timelines) {
		this.myTimelines = timelines;
	}

	public List<Timeline> getTimelinesToApply() {
		return myTimeslinesToApply;
	}
	
	public void setTimelinesToApply(List<Timeline> timelinesToApply) {
		this.myTimeslinesToApply = timelinesToApply;
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

	public void addTimelines(List<Timeline> timelines) {
		myTimelines.addAll(timelines);
	}

	public boolean isEncapsulated() {
		return isEncapsulated;
	}
	
	public void setEncapsulated(boolean encapsulated){
		this.isEncapsulated = encapsulated;
	}

}