package auth_environment.delegatesAndFactories;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.ContextMenu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserDelegate {
	
	public FileChooserDelegate() {}
	
    public File chooseFile(String title) {
		return this.fileFromWindow(this.initChooser(title));
    }
    
    public File chooseImage(String title) {
    	return this.fileFromWindow(this.addImageFilter(this.initChooser(title)));
    }
    
    private File fileFromWindow(FileChooser f) {
        ContextMenu prefWindow = new ContextMenu();
        File file = f.showOpenDialog(prefWindow.getOwnerWindow());
        return file;
    }
    
    private FileChooser initChooser(String title) {
    	FileChooser f = new FileChooser();
    	f.setTitle(title);
    	return f; 
    }
    
    private FileChooser addImageFilter(FileChooser f) {
    	// TODO: do these need to be extracted? 
    	f.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    	return f;
    }

}
