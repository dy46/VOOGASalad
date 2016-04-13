package auth_environment.buildingBlocks;

import java.util.List;
import game_engine.properties.*;
import javafx.scene.image.ImageView; 

public class EnemyBuildingBlock extends BuildingBlock{
	
	private Price myPrice;

	public EnemyBuildingBlock(ImageView image, String name, double health, double damage, String teamName, double speed,
			double direction, double price, List<Position> positionList, State s) {
		super(image, name, health, damage, teamName, speed, direction, positionList, s);
		myPrice = new Price(price); 

	}
	
	
	
	
}
