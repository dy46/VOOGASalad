package game_player.view;

import java.io.File;
import java.io.FileWriter;
import java.util.ResourceBundle;
import game_player.interfaces.IGameView;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class OptionsMenu extends PlayerMenu {

    private static final int WINDOW_SIZE = 500;
    private Stage menuStage;
    private ResourceBundle myResources;
    private MenuMaker menuMaker;
    private Menu myMenu;
    private GridPane myRoot;
    private SwitchWindow mySwitchWindow;
    private IGameView myView;

    public OptionsMenu (ResourceBundle r, IGameView view) {
        super(r, view);
        myResources = r;
        menuMaker = new MenuMaker(r);
        mySwitchWindow = new SwitchWindow(r);
        myView = view;
    }

    public Menu createMenu () {
        myMenu = super.createNewMenu("OptionsMenu");
        return myMenu;
    }

    protected void openSwitchWindow () {
        menuStage = new Stage();
        myRoot = mySwitchWindow.createWindow();
        Scene switchWindowScene = new Scene(myRoot, WINDOW_SIZE, WINDOW_SIZE);
        menuStage.setScene(switchWindowScene);
        menuStage.show();
    }

    protected void restartGame () {
        myView.restartGame();
    }

    protected void saveGame () {
        menuStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(menuStage);
        // TODO: incorporate xstream
        try {
            saveFile("Hello", file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String loadGame () {
        menuStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(menuStage);
        // TODO: do something when it loads the file
        try {
            return file.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected void saveFile (Object content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content.toString());
            fileWriter.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void returnToAuth() {
    	myView.getMainView().displayAuth();
    }
}
