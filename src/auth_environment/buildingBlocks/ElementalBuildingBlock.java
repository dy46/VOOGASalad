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

	
}
