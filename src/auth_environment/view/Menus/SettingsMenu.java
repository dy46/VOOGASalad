package auth_environment.view.Menus;

import java.io.File;
import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by BrianLin on 4/1/16.
 */
public class SettingsMenu extends Menu {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private FileChooserDelegate myFileChooser = new FileChooserDelegate(); 
	
	public SettingsMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(myNamesBundle.getString("settingsMenuLabel"));
		MenuItem musicItem = new MenuItem(myNamesBundle.getString("musicItemLabel"));
		musicItem.setOnAction(e -> this.chooseMusic());
		this.getItems().addAll(musicItem); 
	}
	
	// TODO: only show .mp3 files for selection
	private void chooseMusic() {
		File file = myFileChooser.chooseFile();
		System.out.println(file.getAbsolutePath());
	}

}
