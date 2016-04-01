package game_engine.properties;

public class UnitProperties {

	private Health myHealth;
	private Damage myDamage;
	private Team myTeam;
	private Velocity myVelocity;
	private Bounds myBounds;
	private Position myPosition;
	
	public UnitProperties(Health health, Damage damage, Team team, Velocity velocity, Bounds bounds, Position position){
		this.myHealth = health;
		this.myDamage = damage;
		this.myTeam = team;
		this.myVelocity = velocity;
		this.myBounds = bounds;
		this.myPosition = position;
	}
	
	public void update(UnitProperties properties){
		this.myHealth = properties.getHealth();
		this.myDamage = properties.getDamage();
		this.myTeam = properties.getTeam();
		this.myVelocity = properties.getVelocity();
		this.myBounds = properties.getBounds();
		this.myPosition = properties.getPosition();
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
}