package auth_environment.Models;

import auth_environment.Models.Interfaces.IUnitView;
import game_engine.game_elements.Unit;
import javafx.scene.image.ImageView;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * The Auth Environment's way of displaying a Unit. 
 * Contains both the Unit and an ImageView to be displayed. 
 * 
 * A class that implements this should extend ImageView.
 */

public class UnitView extends ImageView implements IUnitView {

	@Override
	public void setUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit getUnit() {
		// TODO Auto-generated method stub
		return null;
	}

}
