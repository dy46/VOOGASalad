package auth_environment.delegatesAndFactories;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserDelegate {
	
	
	
	public FileChooserDelegate() {
		
	}
	
    public File getFileFromFileChooser() {
    	Stage stage = new Stage(); 
        FileChooser f = new FileChooser();
        f.setTitle("Choose file"); // TODO: extract to resources file
        File selectedFile = f.showOpenDialog(stage);
        return selectedFile;
    }

}
