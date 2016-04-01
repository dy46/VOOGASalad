package auth_environment.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class is responsible for loading and saving Game Data.
 */

// TODO: deprecate this class and move functionality to Save and Load menu items in FileMenu.java

public class SaveLoadDisplay {

    private Stage myStage;

    // loads existing Game Data into the Auth Environment for further editing
    public void load() {
        File file = this.getFileFromFileChooser();
    }

    // saves current state to Game Data
    public void save() {}

    private File getFileFromFileChooser() {
        FileChooser f = new FileChooser();
        f.setTitle("Choose file"); // TODO: extract to resources file
        File selectedFile = f.showOpenDialog(myStage);
        return selectedFile;
    }
}
