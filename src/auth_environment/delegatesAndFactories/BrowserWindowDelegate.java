package auth_environment.delegatesAndFactories;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserWindowDelegate {
	
	private Pane loadingPane;
	private ProgressBar loadProgress;
	private Label progressText;
	
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
	
	  private void showMainStage() {
		    mainStage = new Stage(StageStyle.DECORATED);
		    mainStage.setTitle("FX Experience");
		    mainStage.setIconified(true);

		    // create a WebView.
		    webView = new WebView();
		    webView.getEngine().load("http://fxexperience.com/");
		    loadProgress.progressProperty().bind(webView.getEngine().getLoadWorker().workDoneProperty().divide(100));

		    // layout the scene.
		    Scene scene = new Scene(webView, 1000, 600);
		    webView.prefWidthProperty().bind(scene.widthProperty());
		    webView.prefHeightProperty().bind(scene.heightProperty());
		    mainStage.setScene(scene);
		    mainStage.show();
		  }
}
