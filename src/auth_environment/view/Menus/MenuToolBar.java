package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MenuToolBar extends MenuBar {
	
	private TabPane myTabPane; 

	public MenuToolBar(TabPane tabPane, PickerMenu myPicker) {
		this.myTabPane = tabPane; 
		this.init(myPicker);
	}
	
	private List<Menu> defaultMenus(PickerMenu myPicker) {
		List<Menu> myMenus = Arrays.asList(
				new FileMenu(), 
				new SettingsMenu(),
				new ElementMenu(myPicker),
				new HelpMenu(), 
				new TabMenu(this.myTabPane));
		return myMenus; 
	}
	
	private void init(PickerMenu myPicker) {
		this.getMenus().addAll(this.defaultMenus(myPicker));
	}
}
