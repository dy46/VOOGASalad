//package auth_environment.buildingBlocks;
//
//import java.util.List;
//
//import game_engine.properties.*;
//import javafx.scene.image.ImageView;
//
///**
// * This BuildingBlock superclass is the external API for the authoring environment. The BuildingBlock object
// * encapsulates the user-customized values of properties for game elements. 
// * @author codyli
// */
//
//public abstract class BuildingBlock {
//	private String myName;
//
//	private Health myHealth;
//	private Damage myDamage;
//	private Team myTeam;
//	//private Velocity myVelocity;
//	private Bounds myBounds;
//	private Price myPrice;
//	private ImageView myImage;
//	private State myState; 
//	private double mySpeed;
//	private double myDirection;
//	
//	public BuildingBlock(){
//		
//	}
//	
//	//Getters and Setters
//	public String getMyName() {
//		return myName;
//	}
//
//	public void setMyName(String myName) {
//		this.myName = myName;
//	}
//
//	public Health getMyHealth() {
//		return myHealth;
//	}
//
////	public void setMyHealth(Health myHealth) {
////		this.myHealth = myHealth;
////	}
//	
//	public void setMyHealth(double myHealth) {
//		this.myHealth = new Health(myHealth);
//	}
//
//
//	public Damage getMyDamage() {
//		return myDamage;
//	}
//
////	public void setMyDamage(Damage myDamage) {
////		this.myDamage = myDamage;
////	}
//
//	public void setMyDamage(double myDamage) {
//		this.myDamage = new Damage(myDamage);
//	}
//	
//	public Team getMyTeam() {
//		return myTeam;
//	}
//
//	public void setMyTeam(Team myTeam) {
//		this.myTeam = myTeam;
//	}
//
//	public Velocity getMyVelocity() {
//		return new Velocity(mySpeed, myDirection);
//	}
//
////	public void setMyVelocity(Velocity myVelocity) {
////		this.mySpeed = myVelocity.getSpeed();
////		this.myDirection = myVelocity.getDirection();
////	}
//	
//	public void setMySpeed(double mySpeed) {
//		this.mySpeed = mySpeed;
//	}
//	
//	public void setMyDirection(double myDirection){
//		this.myDirection = myDirection;
//	}
//
//	public Bounds getMyBounds() {
//		return myBounds;
//	}
//
//	public void setMyBounds(Bounds myBounds) {
//		this.myBounds = myBounds;
//	}
//
//	public Price getMyPrice() {
//		return myPrice;
//	}
//
////	public void setMyPrice(Price myPrice) {
////		this.myPrice = myPrice;
////	}
//	
//	public void setMyPrice(double myPrice) {
//		this.myPrice = new Price(myPrice);
//	}
//
//	public ImageView getMyImage() {
//		return myImage;
//	}
//
//	public void setMyImage(ImageView myImage) {
//		this.myImage = myImage;
//	}
//	
//	public State getMyState() {
//		return myState;
//	}
//
//	public void setMyState(State myState) {
//		this.myState = myState;
//	}
//		
//}