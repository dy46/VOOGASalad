
package auth_environment.buildingBlocks;

import java.util.List;
import game_engine.properties.*;
import javafx.scene.image.ImageView; 

public class EnemyBuildingBlock extends BuildingBlock{

	private Price myPrice;
	private String myBehavior; 
	private String myProperty; 
	public EnemyBuildingBlock() {
		
	}

	public Price getMyPrice() {
		return myPrice;
	}

	public void setMyPrice(Price myPrice) {
		this.myPrice = myPrice;
	}
	
	public String getMyBehavior() {
		return myBehavior;
	}

	public void setMyBehavior(String myBehavior) {
		this.myBehavior = myBehavior;
	}

	public String getMyProperty() {
		return myProperty;
	}

	public void setMyProperty(String myProperty) {
		this.myProperty = myProperty;
	}


	
}


