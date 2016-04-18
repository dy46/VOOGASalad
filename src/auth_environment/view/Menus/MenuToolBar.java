package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import auth_environment.backend.GameDataController;
import auth_environment.backend.ISettings;
import auth_environment.view.ElementPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuToolBar extends MenuBar {
	
	private ISettings mySettings;
	private GameDataController myGameDataController;
	
	public MenuToolBar(ElementPicker myPicker, ISettings settings, GameDataController gameDataController) {
		this.mySettings = settings;
		this.myGameDataController = gameDataController;
		this.init(myPicker);
	}

	private List<Menu> defaultMenus(ElementPicker myPicker) {
		List<Menu> myMenus = Arrays.asList(
				new FileMenu(this.mySettings, myGameDataController), 
				new SettingsMenu(),
				new ElementMenu(myPicker),
				new HelpMenu()
				);
		return myMenus; 
	}
	
	private void init(ElementPicker myPicker) {
		this.getMenus().addAll(this.defaultMenus(myPicker));
	}
}