package auth_environment.view.Menus;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;

/**
 * Created by BrianLin on 4/1/16.
 */
public class SettingsMenu extends Menu {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public SettingsMenu() {
		this.setText(myNamesBundle.getString("settingsMenuLabel"));
		this.init();
	}
	
	private void init() {
	
	}
}
