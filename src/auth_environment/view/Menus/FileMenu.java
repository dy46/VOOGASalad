package auth_environment.view.Menus;

import java.io.File;

import auth_environment.delegatesAndFactories.FileChooserDelegate;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible:
 * 
 * This drop down menu should allow the Developer to 1) Save 2) Load 
 */
public class FileMenu extends Menu {
	
	// TODO: ask team where to extract these constants
	private static String saveItemLabel = "Save"; 
	private static String loadItemlabel = "Load"; 
	private static String fileMenuLabel = "File";

	private FileChooserDelegate myFileChooser = new FileChooserDelegate(); 

	
	public FileMenu() {
		this.init();
	}
	
	// TODO: setup these items in a Node factory of some sort? 
	
	private void init() {
		this.setText(this.fileMenuLabel);
		MenuItem saveItem = new MenuItem(this.saveItemLabel);
		saveItem.setOnAction(e -> this.save());
		MenuItem loadItem = new MenuItem(this.loadItemlabel); 
		loadItem.setOnAction(e -> this.load());
		this.getItems().addAll(saveItem, loadItem); 
	}
	
	// TODO: should pass ONE object to XStream. (We need to decide how to store that) 
	private void save() {
	
	}
	
	private void load() {
		File file = myFileChooser.chooseFile();
	}
	
}
