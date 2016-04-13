package auth_environment.delegatesAndFactories;

import java.io.File;

import javafx.scene.control.ContextMenu;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserDelegate {
	
	public FileChooserDelegate() {}
	
    public File chooseFile(String title) {
		return this.fileFromWindow(this.initChooser(title));
    }
    
    public File chooseImage(String title) {
    	return this.fileFromWindow(this.addImageFilter(this.initChooser(title)));
    }
    
    public File chooseXML(String title) {
    	return this.fileFromWindow(this.addXMLFilter(this.initChooser(title)));
    }
    
    // TODO: refactor
    public File save(String title) {
    	ContextMenu prefWindow = new ContextMenu();
        File file = this.initChooser(title).showSaveDialog(prefWindow.getOwnerWindow());
        return file;
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
    
    private FileChooser addXMLFilter(FileChooser f) {
    	f.getExtensionFilters().add(new ExtensionFilter("*.xml")); 
    	return f; 
    }

}
