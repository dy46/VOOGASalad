package auth_environment.view.Menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible:
 * 
 * This drop down menu should allow the Developer to 1) Save 2) Load 
 */
public class FileMenu extends Menu {
	
	// TODO: ask team where to extract these constants
	private static String saveItemLabel = "Save"; 
	private static String loadItemlabel = "Load"; 
	
	public FileMenu() {
		this.init();
	}
	
	// TODO: setup these items in a Node factory of some sort? 
	
	private void init() {
		MenuItem saveItem = new MenuItem(this.saveItemLabel);
		MenuItem loadItem = new MenuItem(this.loadItemlabel); 
		this.getItems().addAll(saveItem, loadItem); 
	}
	
}
