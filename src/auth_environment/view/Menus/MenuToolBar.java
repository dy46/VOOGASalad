package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

public class MenuToolBar extends MenuBar {
	
	// Note: add Menus here!
	private List<Menu> myMenus = Arrays.asList(
			new FileMenu(), 
			new SettingsMenu(),
			new HelpMenu(), 
			new TabMenu(this.myTabPane));
	
	private TabPane myTabPane; 

	public MenuToolBar(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.init();
	}
	
	private void init() {
		this.getMenus().addAll(this.myMenus);
	}
}
