
package auth_environment.buildingBlocks;

import java.util.List;
import game_engine.properties.*;
import javafx.scene.image.ImageView; 

public class EnemyBuildingBlock extends BuildingBlock{

	private String myBehavior; 
	private String myProperty; 
	public EnemyBuildingBlock() {
		
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


