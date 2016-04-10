package auth_environment.buildingBlocks;

import java.util.List;

import game_engine.properties.Bounds;
import game_engine.properties.Damage;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.Price;
import game_engine.properties.Team;
import game_engine.properties.Velocity;
import javafx.scene.image.ImageView;

/**
 * This BuildingBlock superclass is the external API for the authoring environment. The BuildingBlock object
 * encapsulates the user-customized values of properties for game elements. 
 * @author codyli
 */

public abstract class BuildingBlock {
	private String myName;

	private Health myHealth;
	private Damage myDamage;
	private Team myTeam;
	private Velocity myVelocity;
	private Bounds myBounds;
	private Price myPrice;
	private ImageView myImage;
	
	public BuildingBlock(ImageView image, String name, double health, double damage, String teamName, double speed, 
			double direction, List<Position> positionList){
		myImage = image;
		myName = name;
		myHealth = new Health(health);
		myDamage = new Damage(damage);
		myTeam = new Team(teamName);
		myVelocity = new Velocity(speed, direction);
		myBounds = new Bounds(positionList);
	}
	
	//Getters and Setters
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Health getMyHealth() {
		return myHealth;
	}

	public void setMyHealth(Health myHealth) {
		this.myHealth = myHealth;
	}

	public Damage getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(Damage myDamage) {
		this.myDamage = myDamage;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;
	}

	public Velocity getMyVelocity() {
		return myVelocity;
	}

	public void setMyVelocity(Velocity myVelocity) {
		this.myVelocity = myVelocity;
	}

	public Bounds getMyBounds() {
		return myBounds;
	}

	public void setMyBounds(Bounds myBounds) {
		this.myBounds = myBounds;
	}

	public Price getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Price myPrice) {
		this.myPrice = myPrice;
	}

	public ImageView getMyImage() {
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}
		
}
