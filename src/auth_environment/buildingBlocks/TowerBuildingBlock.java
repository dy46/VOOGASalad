package auth_environment.buildingBlocks;

import java.util.List;

import game_engine.properties.*;
import javafx.scene.image.ImageView;

public class TowerBuildingBlock extends BuildingBlock{

	private Price myPrice;
	private double range; 
	private double firingRate; 

	public TowerBuildingBlock(ImageView image, String name, double health, double damage, String teamName, double speed, double direction,
			double r, double price, List<Position> positionList, double fRate){
		super(image, name, health, damage, teamName, speed, direction, positionList); 
		myPrice = new Price(price);
		range = r;
		firingRate = fRate; 
	}
	
	//Getters and Setters 
	
	public Price getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Price myPrice) {
		this.myPrice = myPrice;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}
	
	public double getFiringRate() {
		return firingRate;
	}

	public void setFiringRate(double firingRate) {
		this.firingRate = firingRate;
	}
	
	
}
