package auth_environment.delegatesAndFactories;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserWindowDelegate {
	
	private WebView browser = new WebView();
    private WebEngine webEngine = browser.getEngine();
	
	public BrowserWindowDelegate() {
		
	}
	
	public void openWindow(String title, String url, double width, double height) {
		Stage stage = new Stage();
		Pane root = new Pane();
        stage.setTitle(title);
		webEngine.load(url);
		root.getChildren().add(browser);
        stage.setScene(new Scene(root, width, height));
        stage.show();
	}
}
