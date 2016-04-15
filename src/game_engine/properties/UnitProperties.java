package game_engine.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.game_elements.Path;

public class UnitProperties {

	private Health myHealth;
	private Damage myDamage;
	private Team myTeam;
	private Velocity myVelocity;
	private Bounds myBounds;
	private Position myPosition;
	private Price myPrice;
	private State myState;
	private Movement myMovement;

	private static String DEFAULT_STATE = "Stationary";
	private static double DEFAULT_HEALTH = 1;
	private static double DEFAULT_DAMAGE = 0;
	private static String DEFAULT_TEAM = "0";
	private static double DEFAULT_SPEED = 1;
	private static double DEFAULT_DIRECTION = 90;
	private static List<Position> DEFAULT_BOUNDS = new ArrayList<Position>();
	private static double DEFAULT_X_POS = 0;
	private static double DEFAULT_Y_POS = 0;
	private static double DEFAULT_PRICE = 0;

	public UnitProperties(Health health, Damage damage, 
			Team team, Velocity velocity, 
			Bounds bounds, Position position, 
			Price price, State state,
			Movement movement){
		this.myHealth = health;
		this.myDamage = damage;
		this.myTeam = team;
		this.myVelocity = velocity;
		this.myBounds = bounds;
		this.myPosition = position;
		this.myPrice = price;
		this.myState = state;
		this.myMovement = movement;
	}

	public UnitProperties copyUnitProperties() {
		UnitProperties newProperties = new UnitProperties();
		newProperties.myHealth = this.getHealth().copyHealth();
		//            newProperties.myDamage = this.getDamage().copyDamage();
		//            newProperties.myTeam = this.getTeam().copyTeam();
		newProperties.myState = this.getState().copyState();
		newProperties.myVelocity = this.getVelocity().copyVelocity();
		newProperties.myBounds = this.getBounds().copyBounds();
		newProperties.myPosition = this.myPosition.copyPosition();
		newProperties.myMovement = new Movement(myMovement.getPaths().stream().map(p -> p.copyPath()).collect(Collectors.toList()));
		//newProperties.myPaths = this.myPath.copyPath();
		//            newProperties.myPrice = this.myPrice.copyPrice();
		return newProperties;
	}

	public UnitProperties(){
		myState = new State(DEFAULT_STATE);
		myHealth = new Health(DEFAULT_HEALTH);
		myDamage = new Damage(DEFAULT_DAMAGE);
		myTeam = new Team(DEFAULT_TEAM);
		myVelocity = new Velocity(DEFAULT_SPEED, DEFAULT_DIRECTION);
		myBounds = new Bounds(DEFAULT_BOUNDS);
		myPosition = new Position(DEFAULT_X_POS, DEFAULT_Y_POS);
		myPrice = new Price(DEFAULT_PRICE);
	}

	public Health getHealth(){
		return myHealth;
	}

	public Damage getDamage(){
		return myDamage;
	}

	public Team getTeam(){
		return myTeam;
	}

	public Velocity getVelocity(){
		return myVelocity;
	}

	public Bounds getBounds(){
		return myBounds;
	}

	public Position getPosition(){
		return myPosition;
	}

	public Price getPrice(){
		return myPrice;
	}

	public void setHealth(double health){
		myHealth.setValue(health);
	}

	public void setDamage(double damage){
		myDamage.setDamage(damage);
	}

	public void setTeam(String team){
		myTeam.setTeam(team);
	}

	public void setVelocity(double speed, double direction){
		myVelocity.setSpeed(speed);
		myVelocity.setDirection(direction);
	}

	public void setBounds(List<Position> positions){
		myBounds.setPositions(positions);
	}
	
	public void setBounds(Bounds b){
		myBounds = b;
	}

	public void setPosition(double x, double y){
		myPosition.setX(x);
		myPosition.setY(y);
	}
	
	public void setPosition(Position pos){
		myPosition = pos;
	}

	public void setState(State state) {
		this.myState = state;
	}

	public State getState() {
		return myState;
	}
	
	public Movement getMovement(){
		return myMovement;
	}

	public void setMovement(Movement movement) {
		this.myMovement = movement;
	}

}