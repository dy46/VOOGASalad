package auth_environment.buildingBlocks;

import javafx.scene.image.ImageView;

public class ElementalBuildingBlock {
	private String myName;

	private int myHealth;
	private int myDamage;
	private String myTeam;
	private int myVelocity;
	private int myBounds;
	private int myPrice;
	private ImageView myImage;
	
	public ElementalBuildingBlock(ImageView image, String name, int health, int damage, String teamName, int speed, int range, int price){
		myImage = image;
		myName = name;
		myHealth = health;
		myDamage = damage;
		myTeam = teamName;
		myVelocity = speed;
		myBounds = range;
		myPrice = price;
	}
	
	//Getters and Setters
	
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyHealth() {
		return myHealth;
	}

	public void setMyHealth(int myHealth) {
		this.myHealth = myHealth;
	}

	public int getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(int myDamage) {
		this.myDamage = myDamage;
	}

	public String getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(String myTeam) {
		this.myTeam = myTeam;
	}

	public int getMyVelocity() {
		return myVelocity;
	}

	public void setMyVelocity(int myVelocity) {
		this.myVelocity = myVelocity;
	}

	public int getMyBounds() {
		return myBounds;
	}

	public void setMyBounds(int myBounds) {
		this.myBounds = myBounds;
	}

	public int getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(int myPrice) {
		this.myPrice = myPrice;
	}

	public ImageView getMyImage() {
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}
	
}
