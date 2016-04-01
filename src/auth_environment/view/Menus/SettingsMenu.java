package auth_environment.view.Menus;

import javafx.scene.control.Menu;

/**
 * Created by BrianLin on 4/1/16.
 */
public class SettingsMenu extends Menu {
	
	// TODO: ask team where to extract these
	private static String settingsMenuLabel = "Game Settings";
	
	public SettingsMenu() {
		this.setText(this.settingsMenuLabel);
		this.init();
	}
	
	private void init() {
	
	}
}
