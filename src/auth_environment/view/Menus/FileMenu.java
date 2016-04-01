package auth_environment.view.Menus;

import java.io.File;

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
	
	private void save() {
	
	}
	
	private void load() {
		File file = this.getFileFromFileChooser();
	}
	
    private File getFileFromFileChooser() {
    	Stage stage = new Stage(); 
        FileChooser f = new FileChooser();
        f.setTitle("Choose file"); // TODO: extract to resources file
        File selectedFile = f.showOpenDialog(stage);
        return selectedFile;
    }
	
}
