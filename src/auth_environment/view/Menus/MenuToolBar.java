package auth_environment.view.Menus;

import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

public class MenuToolBar extends MenuBar {
	
	private TabPane myTabPane; 

	public MenuToolBar(TabPane tabPane) {
		this.myTabPane = tabPane; 
		this.init();
	}
	
	private void init() {
		this.getMenus().addAll(new FileMenu(), new HelpMenu(), new TabMenu(this.myTabPane));
	}
	
	public void addMenuItem(String name) {
		
	}

}
