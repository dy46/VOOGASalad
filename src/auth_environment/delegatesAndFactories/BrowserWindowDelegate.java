package auth_environment.delegatesAndFactories;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BrowserWindowDelegate {
	
	public BrowserWindowDelegate() {
		
	}
	
	public void openHelpWindow() {
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
