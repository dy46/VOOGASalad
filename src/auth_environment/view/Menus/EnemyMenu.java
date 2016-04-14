package auth_environment.view.Menus;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.EnemyBuildingBlock;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class EnemyMenu extends SuperMenu{
	
	public EnemyMenu(PickerMenu myPicker) {
		super(myPicker);
		// TODO Auto-generated constructor stub
	}
	private static final String ENEMY_LABELS_PACKAGE = "auth_environment/properties/enemy_labels";

	private Map<String, TextField> intTextMap = new HashMap<String, TextField>();
	private Map<String, TextField> strTextMap = new HashMap<String, TextField>();
	@Override
	public void createNewElement() {
		// TODO Auto-generated method stub
		ResourceBundle myEnemiesBundle = ResourceBundle.getBundle(ENEMY_LABELS_PACKAGE);
		createElement(myEnemiesBundle, new EnemyBuildingBlock(), intTextMap, strTextMap);
		
	}
	@Override
	public void makeElement(Tooltip t, BuildingBlock block) {
		// TODO Auto-generated method stub
		
	}

}
