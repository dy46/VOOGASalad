package auth_environment.view.Menus;

import java.io.File;
import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible:
 * 
 * This drop down menu should allow the Developer to 1) Save 2) Load 
 */
public class FileMenu extends Menu {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private FileChooserDelegate myFileChooser = new FileChooserDelegate(); 
	
	public FileMenu() {
		this.init();
	}
	
	// TODO: setup these items in a Node factory of some sort?
	private void init() {
		this.setText(myNamesBundle.getString("fileMenuLabel"));
		MenuItem saveItem = new MenuItem(myNamesBundle.getString("saveItemLabel"));
		saveItem.setOnAction(e -> this.save());
		MenuItem loadItem = new MenuItem(myNamesBundle.getString("loadItemLabel")); 
		loadItem.setOnAction(e -> this.load());
		this.getItems().addAll(saveItem, loadItem); 
	}
	
	// TODO: should pass ONE object to XStream. (We need to decide how to store that) 
	private void save() {
	
	}
	
	private void load() {
		File file = myFileChooser.chooseFile(myNamesBundle.getString("fileChooserTitle"));
		System.out.println(file.getAbsolutePath());
	}
	
}
