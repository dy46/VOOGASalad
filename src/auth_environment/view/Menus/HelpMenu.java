package auth_environment.view.Menus;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 * 
 * Team member responsible: 
 * 
 * This menu 
 */

public class HelpMenu extends Menu {
	
	// TODO: ask team where to extract these
	private static String helpURL = "https://gist.github.com/Briguy52/7adf8cfe508e73267bb46ce2aebe7b4a"; 
	private static String helpText = "Help";
	private static int helpWidth = 800;
	private static int helpHeight = 600; 
	
	private WebView browser = new WebView();
    private WebEngine webEngine = browser.getEngine();
	
	public HelpMenu() {
		this.setText(this.helpText);
		this.setOnAction(e -> openHelpWindow());
	}
	
	private void openHelpWindow() {
		Stage stage = new Stage();
		Pane root = new Pane();
        stage.setTitle(this.helpText);
		webEngine.load(this.helpURL);
		root.getChildren().add(browser);
		int width = this.helpWidth; // NOTE: eventually use parseInt(<width from properties file>) 
		int height = this.helpHeight;
        stage.setScene(new Scene(root, width, height));
        stage.show();
	}
}
