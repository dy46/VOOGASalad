package auth_environment.buildingBlocks;

import java.util.List;

import game_engine.properties.*;
import javafx.scene.image.ImageView;

public class TowerBuildingBlock extends BuildingBlock{

	private Price myPrice;
	private double range; 
	private double firingRate; 
	//Getters and Setters 
	
	public TowerBuildingBlock() {
		// TODO Auto-generated constructor stub
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