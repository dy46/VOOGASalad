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
		ImageView loadingImage = new ImageView(new Image("http://fxexperience.com/wp-content/uploads/2010/06/logo.png"));
	    loadProgress = new ProgressBar();
	    loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
	    progressText = new Label("Loading hobbits with pie . . .");
	    splashLayout = new VBox();
	    splashLayout.getChildren().addAll(splash, loadProgress, progressText);
	    progressText.setAlignment(Pos.CENTER);
	    splashLayout.setStyle("-fx-padding: 5; -fx-background-color: cornsilk; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, chocolate, derive(chocolate, 50%));");
	    splashLayout.setEffect(new DropShadow());
	}
	
	public void openWindow(String title, String url, double width, double height) {
		Stage stage = new Stage();
		Pane root = new Pane();
        stage.setTitle(title);
        stage.setIconified(true);
		webEngine.load(url);
	    loadProgress.progressProperty().bind(browser.getEngine().getLoadWorker().workDoneProperty().divide(100));
		root.getChildren().add(browser);
		Scene scene = new Scene(root, width, height);
	    browser.prefWidthProperty().bind(scene.widthProperty());
	    browser.prefHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.show();
	}
	
	  private void showSplash(Stage initStage) {
		    Scene splashScene = new Scene(splashLayout);
		    initStage.initStyle(StageStyle.UNDECORATED);
		    final Rectangle2D bounds = Screen.getPrimary().getBounds();
		    initStage.setScene(splashScene);
		    initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		    initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		    initStage.show();
		  }
}
