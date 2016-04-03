package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MenuToolBar extends MenuBar {
	
	private Stage mainStage; 
	private TabPane myTabPane; 

	public MenuToolBar(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.init();
	}
	
	private List<Menu> defaultMenus() {
		List<Menu> myMenus = Arrays.asList(
				new FileMenu(), 
				new SettingsMenu(),
				new ElementMenu(),
				new HelpMenu(), 
				new TabMenu(this.myTabPane));
		return myMenus; 
	}
	
	private void init() {
		this.getMenus().addAll(this.defaultMenus());
	}
}
