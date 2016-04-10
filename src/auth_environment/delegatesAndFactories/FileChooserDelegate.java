package auth_environment.delegatesAndFactories;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.ContextMenu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserDelegate {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public FileChooserDelegate() {
		
	}
	
    public File chooseFile() {
		return this.fileFromWindow(this.initChooser());
    }
    
    private File fileFromWindow(FileChooser f) {
        ContextMenu prefWindow = new ContextMenu();
        File file = f.showOpenDialog(prefWindow.getOwnerWindow());
        return file;
    }
    
    private FileChooser initChooser() {
    	FileChooser f = new FileChooser();
        f.setTitle(myNamesBundle.getString("fileChooserTitle")); 
        return f; 
    }
    
    public File chooseImage() {
    	return this.fileFromWindow(this.addImageFilter(this.initChooser()));
    	
    }
    
    private FileChooser addImageFilter(FileChooser f) {
    	// TODO: do these need to be extracted? 
    	f.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    	return f;
    }

}
