package auth_environment.view.Menus;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {


	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	
	private PickerMenu myPicker;
	
	
	public ElementMenu(PickerMenu myPicker) {  ///MAKE SUPER CLAASSS + REFACTOR
		this.myPicker = myPicker;
		this.init();
	}
	
	private void init() {
		TowerMenu myTowerMenu = new TowerMenu(myPicker);
		EnemyMenu myEnemyMenu = new EnemyMenu(myPicker);
		TerrainMenu myTerrainMenu = new TerrainMenu(myPicker);
		this.setText(this.myNamesBundle.getString("elementMenuLabel"));
		MenuItem towerItem = new MenuItem(this.myNamesBundle.getString("towerItemLabel"));
		MenuItem terrainItem = new MenuItem(this.myNamesBundle.getString("terrainItemLabel"));
		MenuItem enemyItem = new MenuItem(this.myNamesBundle.getString("enemyItemLabel"));
		towerItem.setOnAction(e -> myTowerMenu.createNewElement());
		terrainItem.setOnAction(e -> myTerrainMenu.createNewElement());
		enemyItem.setOnAction(e -> myEnemyMenu.createNewElement());
		this.getItems().addAll(towerItem, terrainItem, enemyItem); 
	}
}