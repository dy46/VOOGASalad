package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import auth_environment.backend.ISettings;
import auth_environment.view.ElementPicker;
import game_data.IGameData;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

public class MenuToolBar extends MenuBar {
	
	private TabPane myTabPane; 
	private IGameData myGameData; 

	public MenuToolBar(TabPane tabPane, ElementPicker myPicker, IGameData gameData) {
		this.myGameData = gameData; 
		this.myTabPane = tabPane; 
		this.init(myPicker);
	}
	
	private List<Menu> defaultMenus(ElementPicker myPicker) {
		List<Menu> myMenus = Arrays.asList(
				new FileMenu(this.myGameData), 
				new SettingsMenu(),
				new ElementMenu(myPicker),
				new HelpMenu(),
				new TabMenu(this.myTabPane, this.myGameData)
				);
		return myMenus; 
	}
	
	private void init(ElementPicker myPicker) {
		this.getMenus().addAll(this.defaultMenus(myPicker));
	}
}
