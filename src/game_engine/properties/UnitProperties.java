package game_engine.properties;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;


public class UnitProperties {

	private Health myHealth;
	private Team myTeam;
	private Velocity myVelocity;
	private Bounds myBounds;
	private Bounds myRange;
	private Position myPosition;
	private Price myPrice;
	private State myState;
	private Movement myMovement;
	private Mass myMass;

	private static final double DEFAULT_STATE = 0;
	private static final double DEFAULT_HEALTH = 1;
	private static final double DEFAULT_TEAM = 0;
	private static final double DEFAULT_SPEED = 1;
	private static final double DEFAULT_DIRECTION = 90;
	private static List<Position> DEFAULT_BOUNDS = new ArrayList<>();
	private static final double DEFAULT_X_POS = 0;
	private static final double DEFAULT_Y_POS = 0;
	private static final double DEFAULT_PRICE = 0;
	private static final double DEFAULT_MASS = 1;
	private static List<Branch> DEFAULT_PATHS = new ArrayList<>();

	public UnitProperties (Health health,
			Team team,
			Velocity velocity,
			Bounds bounds,
			Bounds range,
			Position position,
			Price price,
			State state,
			Movement movement, Mass mass) {
		this.myHealth = health;
		this.myTeam = team;
		this.myVelocity = velocity;
		this.myBounds = bounds;
		this.myPosition = position;
		this.myPrice = price;
		this.myState = state;
		this.myMovement = movement;
		this.myRange = range;
		this.myMass = mass;
	}

	public UnitProperties copyUnitProperties() {
		UnitProperties n2 = new UnitProperties();
		// TODO: look into reflection for this
		// getProperty().copyProperty()
		for(Field f : n2.getClass().getDeclaredFields()){
			if(f.getName().matches("my[A-Za-z]*")){
				String prop = f.getName().substring(2);
				try {
					f.set(n2, f.get(this).getClass().getMethod("copy"+f.get(this).getClass().getSimpleName(),
							new Class[0]).invoke(f.get(this), new Object[0]));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return n2;
	}

	public UnitProperties () {
		myState = new State(DEFAULT_STATE);
		myHealth = new Health(DEFAULT_HEALTH);
		myTeam = new Team(DEFAULT_TEAM);
		myVelocity = new Velocity(DEFAULT_SPEED, DEFAULT_DIRECTION);
		myBounds = new Bounds(DEFAULT_BOUNDS);
		myRange = new Bounds(DEFAULT_BOUNDS);
		myPosition = new Position(DEFAULT_X_POS, DEFAULT_Y_POS);
		myPrice = new Price(DEFAULT_PRICE);
		myMovement = new Movement(DEFAULT_PATHS);
		myMass = new Mass(DEFAULT_MASS);
	}

	public Health getHealth () {
		return myHealth;
	}

	public Team getTeam () {
		return myTeam;
	}

	public Velocity getVelocity () {
		return myVelocity;
	}

	public Bounds getBounds () {
		return myBounds;
	}

	public Position getPosition () {
		return myPosition;
	}

	public void setPosition(Position pos){
		if(myPosition == null){
			myPosition = new Position(pos.getX(), pos.getY());
		}
		myPosition.setX(pos.getX());
		myPosition.setY(pos.getY());
	}

	public Price getPrice () {
		return myPrice;
	}

	public void setPrice(double price){
		this.myPrice.setPrice(price);
	}

	public void setPriceProp(Price price){
		this.myPrice = price;
	}

	public void setHealth (double health) {
		myHealth.setValue(health);
	}

	public void setHealthProp(Health health) {
		myHealth = health;
	}

	public void setTeam (double team) {
		this.myTeam.setTeam(team);
	}

	public void setTeamProp (Team team) {
		this.myTeam = team;
	}

	public void setVelocity (double speed, double direction) {
		myVelocity.setSpeed(speed);
		myVelocity.setDirection(direction);
	}

	public void setVelocityProp (Velocity velocity) {
		this.myVelocity = velocity;
	}

	public void setBounds (List<Position> positions) {
		myBounds.setPositions(positions);
	}

	public void setBounds (Bounds b) {
		myBounds = b;
	}

	public void setPosition (double x, double y) {
		myPosition.setX(x);
		myPosition.setY(y);
	}

	public void setPositionProp (Position pos) {
		myPosition = pos;
	}

	public void setStateProp (State state) {
		this.myState = state;
	}

	public void setState(double state){
		this.myState.setState(state);
	}

	public State getState () {
		return myState;
	}

	public Movement getMovement () {
		return myMovement;
	}

	public void setMovement (Movement movement) {
		this.myMovement = movement;
	}

	public void setRange (Bounds range) {
		this.myRange = range;
	}

	public Bounds getRange () {
		return myRange;
	}

	

	public void setMass(double mass) {
		this.myMass.setMass(mass);
	}

	public void setMassProp(Mass mass){
		this.myMass = mass;
	}

	public Mass getMass(){
		return myMass;
	}

	public static void main(String[] args){
		UnitProperties u = new UnitProperties();
		u.setHealth(100);
		u.setVelocity(100, 3);
		u.copyUnitProperties();
	}

}
