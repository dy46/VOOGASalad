package auth_environment.delegatesAndFactories;

import java.io.File;
import java.util.ResourceBundle;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserDelegate {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public FileChooserDelegate() {
		
	}
	
    public File chooseFile() {
    	Stage stage = new Stage(); 
        FileChooser f = new FileChooser();
        f.setTitle(myNamesBundle.getString("fileChooserTitle")); 
        File selectedFile = f.showOpenDialog(stage);
        return selectedFile;
    }

}
