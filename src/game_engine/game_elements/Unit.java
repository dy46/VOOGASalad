package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class Unit extends GameElement {

	private List<Unit> parents;
	private UnitProperties myProperties;

	private List<Affector> myAffectors;
	private List<Affector> myAffectorsToApply;

	private int TTL;
	private boolean setToDeath;
	private boolean hasCollided;
	private boolean isEncapsulated;
	private int deathDelay;
	private int elapsedTime;
	private int numFrames;
	private List<Double> numberList;
	private List<Unit> myChildren;
	private String type;

	private String imgName;

	public Unit (String name, List<Affector> affectors, int numFrames) {
		super(name);
		initialize();
		myProperties = new UnitProperties();
		elapsedTime = 0;
		this.numFrames = numFrames;
		addAffectors(affectors);
		myChildren = new ArrayList<>();
		parents = new ArrayList<>();
		this.type = name;
	}

	public Unit(String name, UnitProperties unitProperties) {
		super(name);
		initialize();
		myProperties = unitProperties;
		elapsedTime = 0;
		this.numFrames = numFrames;
		this.myChildren = new ArrayList<>();
		this.type = name;
	}

	public Unit (String name, int numFrames) {
		super(name);
		initialize();
		myProperties = new UnitProperties();
		elapsedTime = 0;
		this.numFrames = numFrames;
		myChildren = new ArrayList<>();
		parents = new ArrayList<>();
		this.type = name;
	}

	public Unit copyUnit() {
		Unit copy = this.copyShallowUnit();
		List<Unit> copiedChildren = this.getChildren().stream().map(u -> u.copyUnit()).collect(Collectors.toList());
		copy.setChildren(copiedChildren);
		return copy;
	}
	public void setType(String newType){
		this.type = newType;
	}

	public Unit copyShallowUnit() {
		Unit copy = new Unit(this.toString(), this.getNumFrames());
		List<Affector> copyApplyAffectors = this.getAffectorsToApply().stream().map(a -> a.copyAffector()).collect(Collectors.toList());
		copy.setAffectorsToApply(copyApplyAffectors);
		List<Affector> copyAffectors = this.getAffectors().stream().map(a -> a.copyAffector()).collect(Collectors.toList());
		copy.setAffectors(copyAffectors);
		copy.setProperties(this.getProperties().copyUnitProperties());
		copy.setDeathDelay(this.getDeathDelay());
		copy.setNumberList(this.getNumberList());
		copy.setTTL(this.getTTL());
		copy.setHasCollided(this.hasCollided);
		copy.elapsedTime = 0;
		return copy;
	}

	private void initialize () {
		this.setHasCollided(false);
		if(myAffectors == null){
			myAffectors = new ArrayList<>();
		}
		if(myAffectorsToApply == null){
			myAffectorsToApply = new ArrayList<>();
		}
	}

	public void update () {
		myChildren.stream().forEach(p -> p.incrementElapsedTime(1));
		if (isVisible()) {
			elapsedTime++;
			myAffectors.removeIf(a -> a.getTTL() <= a.getElapsedTime());
			//            System.out.println("Hello");
			//            myTimelines.forEach(t -> System.out.println(t));
			myAffectors.forEach(a -> a.apply(this));
			//            System.out.println(myAffectors);
			//            System.out.println(myAffectors.size());
		}
		if (!isAlive()) {
			System.out.println("DYING");
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

	//    public List<AffectorTimeline> getTimelines () {
	//        return myTimelines;
	//    }
	//
	//    public void setTimelines (List<AffectorTimeline> timelines) {
	//        this.myTimelines = timelines;
	//    }
	//
	//    public List<AffectorTimeline> getTimelinesToApply () {
	//        return myTimeslinesToApply;
	//    }
	//
	//    public void setTimelinesToApply (List<AffectorTimeline> timelinesToApply) {
	//        this.myTimeslinesToApply = timelinesToApply;
	//    }

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

	public List<Unit> getChildren() {
		return myChildren;
	}

	public void setChildren(List<Unit> children) {
		this.myChildren = children;
	}

	public void addChild(Unit child) {
		this.myChildren.add(child);
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

	public void addChildren(List<Unit> children) {
		this.myChildren.addAll(children);
	}

	public void addAffectors(List<Affector> affectors) {
		this.myAffectors.addAll(affectors);
	}

	public void addAffectorsToApply(List<Affector> affectorsToApply){
		this.myAffectorsToApply.addAll(affectorsToApply);
	}

	public void addAffector(Affector affector){
		//		System.out.println(affector.getTTL());
		this.myAffectors.add(affector);
		//		System.out.println(this.myAffectors);
	}

	public void addAffectorToApply(Affector affectorToApply){
		this.myAffectorsToApply.add(affectorToApply);
	}

	public List<Affector> getAffectors(){
		return myAffectors;
	}

	public List<Affector> getAffectorsToApply(){
		return myAffectorsToApply;
	}

	public void setAffectors(List<Affector> affectors) {
		myAffectors = affectors;
	}

	public void setAffectorsToApply(List<Affector> affectorsToApply) {
		myAffectorsToApply = affectorsToApply;
	}

	public void kill(){
		this.setElapsedTimeToDeath();
	}

	public void removeAffectorsByName(String name){
		for(int x=0; x<myAffectors.size(); x++){
			if(myAffectors.get(x).getClass().equals(name)){
				myAffectors.remove(x);
				x--;
			}
		}
	}

	public String getImgName(){
		return imgName;
	}

	public void setImgName(String img){
		this.imgName = img;
	}

	public String getType(){
		return type;
	}

}
