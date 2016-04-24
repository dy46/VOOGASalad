package game_engine.properties;

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
    private List<Unit> myChildren;

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
		UnitProperties newProperties = new UnitProperties();
		// TODO: look into reflection for this
		if(this.getHealth() != null) {
		   newProperties.myHealth = this.getHealth().copyHealth();
		}
		if(this.getTeam() != null) {
		    newProperties.myTeam = this.getTeam().copyTeam();
		}
		newProperties.myState = this.getState().copyState();
		if(this.myVelocity != null) {
		    newProperties.myVelocity = this.getVelocity().copyVelocity();
		}
		if(this.myBounds != null) {
		    newProperties.myBounds = this.getBounds().copyBounds();
		}
		if(this.myPosition != null) {
	            newProperties.myPosition = this.myPosition.copyPosition(); 
		}
		if(this.getMovement() != null) {
		    newProperties.myMovement = this.myMovement.copyMovement();
		}
		if(this.getRange() != null) {
		    newProperties.myRange = this.getRange().copyBounds();
		}
		if(this.getPrice() != null) {
		    this.myPrice = this.getPrice().copyPrice();
		}
		if(this.getMass() != null){
			this.myMass = this.getMass().copyMass();
		}
		return newProperties;
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
    
    public List<Unit> getChildren() {
        return myChildren;
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

}
