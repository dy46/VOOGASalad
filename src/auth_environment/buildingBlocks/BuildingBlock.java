package auth_environment.buildingBlocks;

import java.util.List;

import game_engine.properties.*;
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
	private State myState; 
	private ImageView myImage;
	
	public BuildingBlock(ImageView image, String name, double health, double damage, String teamName, double speed, 
			double direction, List<Position> positionList, State s){
		myImage = image;
		myName = name;
		myHealth = new Health(health);
		myDamage = new Damage(damage);
		myTeam = new Team(teamName);
		myVelocity = new Velocity(speed, direction);
		myBounds = new Bounds(positionList);
		myState = s;
	}
	
	//Getters and Setters
	public String getMyName() {
		return myName;
	}
	
	public State getMyState(){
		return myState;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Health getMyHealth() {
		return myHealth;
	}

	public void setMyHealth(double myHealth) {
		this.myHealth = new Health(myHealth);
	}

	public Damage getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(double myDamage) {
		this.myDamage = new Damage(myDamage);
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
