package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.AffectorTimeline;
import game_engine.properties.UnitProperties;


/**
 * This class is the superclass for game units and implements GameElement.
 * It represents any physical game unit and holds its ID, UnitProperties, and list of current
 * Affectors to be applied.
 * 
 * @author adamtache
 *
 */
public class Unit extends GameElement {

    private List<Unit> parents;
    private UnitProperties myProperties;
    private List<AffectorTimeline> myTimelines;
    private List<AffectorTimeline> myTimeslinesToApply;
    private int TTL;
    private boolean setToDeath;
    private boolean hasCollided;
    private boolean isEncapsulated;
    private int deathDelay;
    private int elapsedTime;
    private int numFrames;
    private List<Double> numberList;
    private List<Unit> children;

    public Unit (String name, List<AffectorTimeline> timelines, int numFrames) {
        super(name);
        initialize();
        myProperties = new UnitProperties();
        elapsedTime = 0;
        this.numFrames = numFrames;
        addTimelines(timelines);
        children = new ArrayList<>();
        parents = new ArrayList<>();
    }
    
    public Unit copyUnit() {
        Unit copy = this.copyShallowUnit();
        List<Unit> copiedChildren = this.getChildren().stream().map(u -> u.copyUnit()).collect(Collectors.toList());
        copy.setChildren(copiedChildren);
        return copy;
    }
    
    public Unit copyShallowUnit() {
        Unit copy = new Unit(this.toString(), this.getNumFrames());
        List<AffectorTimeline> copyApplyTimelines = this.getTimelinesToApply().stream().map(t -> t.copyTimeline()).collect(Collectors.toList());
        copy.setTimelinesToApply(copyApplyTimelines);
        List<AffectorTimeline> copyTimelines = this.getTimelines().stream().map(t -> t.copyTimeline()).collect(Collectors.toList());
        copy.setTimelines(copyTimelines);
        copy.setProperties(this.getProperties().copyUnitProperties());
        copy.setDeathDelay(this.getDeathDelay());
        copy.setNumberList(this.getNumberList());
        copy.setTTL(this.getTTL());
        copy.setHasCollided(this.hasCollided);
        copy.setEncapsulated(this.isEncapsulated);
        copy.elapsedTime = 0;
        return copy;
    }

    public Unit (String name, int numFrames) {
        super(name);
        initialize();
        myProperties = new UnitProperties();
        elapsedTime = 0;
        this.numFrames = numFrames;
        children = new ArrayList<>();
        parents = new ArrayList<>();
    }
    
    private void initialize () {
        myTimelines = new ArrayList<>();
        myTimeslinesToApply = new ArrayList<>();
        this.setHasCollided(false);
    }

    public void update () {
        children.stream().forEach(p -> p.incrementElapsedTime(1));
        if (isVisible()) {
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
        if (myProperties.getHealth() == null)
            return false;
        return myProperties.getHealth().getValue() > 0;
    }

    public void setInvisible () {
        this.setElapsedTime(this.getTTL());
    }

    public UnitProperties getProperties () {
        return myProperties;
    }

    public void setProperties (UnitProperties properties) {
        this.myProperties = properties;
    }

    public List<AffectorTimeline> getTimelines () {
        return myTimelines;
    }

    public void setTimelines (List<AffectorTimeline> timelines) {
        this.myTimelines = timelines;
    }

    public List<AffectorTimeline> getTimelinesToApply () {
        return myTimeslinesToApply;
    }

    public void setTimelinesToApply (List<AffectorTimeline> timelinesToApply) {
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

    public void setDeathDelay (int deathDelay) {
        this.deathDelay = deathDelay;
    }

    public int getDeathDelay () {
        return deathDelay;
    }

    public void incrementTTL (int increment) {
        this.TTL += increment;
    }

    public void setElapsedTimeToDeath () {
        if (!setToDeath) {
            this.setElapsedTime(this.getTTL() - deathDelay);
            this.getProperties().getState().changeState(5);
            setToDeath = true;
        }
    }

    public boolean isVisible () {
        return this.getTTL() >= this.getElapsedTime();
    }

    public void incrementElapsedTime (int i) {
        this.elapsedTime += 1;
    }

    public void setElapsedTime (int newTime) {
        elapsedTime = newTime;
    }

    public boolean hasCollided () {
        return hasCollided;
    }

    public void setHasCollided (boolean hasCollided) {
        this.hasCollided = hasCollided;
    }

    public int getNumFrames () {
        return numFrames;
    }

    public double getSellPrice () {
        return getProperties().getPrice().getValue();
    }

  
	public List<Double> getNumberList () {
        return numberList;
    }

    public void setNumberList (List<Double> numberList) {
        this.numberList = numberList;
    }

    public void addTimelines (List<AffectorTimeline> timelines) {
        myTimelines.addAll(timelines);
    }

    public boolean isEncapsulated () {
        return isEncapsulated;
    }

    public void setEncapsulated (boolean encapsulated) {
        this.isEncapsulated = encapsulated;
    }
    
    public List<Unit> getChildren() {
        return children;
    }
    
    public void setChildren(List<Unit> children) {
        this.children = children;
    }
    
    public void addChild(Unit child) {
        this.children.add(child);
    }
    
    public List<Unit> getParents() {
        return parents;
    }
    
    public void addParent(Unit parent) {
        this.parents.add(parent);
    }
    
    public void setParents(List<Unit> parents) {
        this.parents = parents;
    }

}
