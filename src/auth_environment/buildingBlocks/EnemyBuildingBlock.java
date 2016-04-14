
package auth_environment.buildingBlocks;

import java.util.List;
import game_engine.properties.*;
import javafx.scene.image.ImageView; 

public class EnemyBuildingBlock extends BuildingBlock{

	private Price myPrice;
	
	public EnemyBuildingBlock() {
		
	}

	public Price getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Price myPrice) {
		this.myPrice = myPrice;
	}

	
}


