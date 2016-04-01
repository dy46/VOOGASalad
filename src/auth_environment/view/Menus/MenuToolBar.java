package auth_environment.view.Menus;

import javafx.scene.control.MenuBar;

public class MenuToolBar extends MenuBar {

	public MenuToolBar() {
		this.init();
	}
	
	private void init() {
		System.out.println("hey");
		this.getMenus().add(new FileMenu());
	}
	
	public void addMenuItem(String name) {
		
	}

}
