package game_player;

import java.io.File;
import java.io.FileWriter;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OptionsMenu implements IMenuInterface{

	private static final int WINDOW_SIZE = 500;
	private Stage menuStage;
	private ResourceBundle myResources;
	private MenuMaker menuMaker;
	private Menu myMenu;
	private GridPane myRoot;
	private SwitchWindow mySwitchWindow;
	
	public OptionsMenu(ResourceBundle r) {
		myResources = r;
		menuMaker = new MenuMaker(r);
		mySwitchWindow = new SwitchWindow(r);
	}
	
	public Menu createMenu() {
		myMenu = menuMaker.addMenu(myResources.getString("OptionsMenu"));
		String[] menuItems = myResources.getString("Options").split("/");
		for (String pair: menuItems) {
			String[] combo = pair.split(",");
			try {
                menuMaker.addMenuItem(combo[0], e -> {
                    try {
                        getClass().getDeclaredMethod(combo[1]).invoke(this);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } , myMenu);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
		}
		return myMenu;
	}
	
	private void openSwitchWindow() {
		menuStage = new Stage();
		myRoot = mySwitchWindow.createWindow();
		Scene switchWindowScene = new Scene(myRoot, WINDOW_SIZE, WINDOW_SIZE);
		menuStage.setScene(switchWindowScene);
		menuStage.show();
	}
	
	private void restartGame() {
		//restart game using game engine
	}
	
	private void saveGame() {
		menuStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(menuStage);
        
        //TODO: incorporate xstream
        try {
        	saveFile("Hello", file);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private String loadGame() {
		menuStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
		File file = fileChooser.showOpenDialog(menuStage);
		
		//TODO: do soemthing when it loads the file
		
		try {
        	return file.toString();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return "";
	}
	
	private void saveFile(Object content, File file){
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content.toString());
            fileWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }
}
