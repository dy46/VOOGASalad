package auth_environment.delegatesAndFactories;

import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;
import javax.swing.text.Document;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.concurrent.Worker.State;


public class BrowserWindowDelegate {

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private Pane loadingPane;
	private ProgressBar loadProgress;
	private Label progressText;
	
	private Stage loadingStage;
	private Stage mainStage;

	private WebView browser = new WebView();
	private WebEngine webEngine = browser.getEngine();

	public BrowserWindowDelegate() {
		this.init();
		this.start(new Stage());

	}
	
	// Source: https://gist.github.com/jewelsea/1588531
	public void init() {
		ImageView loadingImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("kurokoheadbang.gif")));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(800 - 20);
		progressText = new Label(myNamesBundle.getString("loadingText"));
		loadingPane = new VBox();
		loadingPane.getChildren().addAll(loadingImage, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		loadingPane.setStyle("-fx-padding: 5; -fx-background-color: cornsilk; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, chocolate, derive(chocolate, 50%));");
		loadingPane.setEffect(new DropShadow());
	}

	public void start(final Stage initStage) {
	    showSplash(initStage);
	    openWindow(myNamesBundle.getString("helpMenuLabel"),
				   myURLSBundle.getString("helpURL"),
				   Double.parseDouble(myDimensionsBundle.getString("helpWidth")),
				   Double.parseDouble(myDimensionsBundle.getString("helpHeight"))
				   );
	    
//	    browser.getEngine().documentProperty().addListener(new ChangeListener() {
//	      @Override public void changed(ObservableValue<? extends Document> observableValue, Document document, Document document1) {
//	        if (initStage.isShowing()) {
//	          loadProgress.progressProperty().unbind();
//	          loadProgress.setProgress(1);
//	          progressText.setText("All hobbits are full.");
//	          mainStage.setIconified(false);
//	          initStage.toFront();
//	          FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
//	          fadeSplash.setFromValue(1.0);
//	          fadeSplash.setToValue(0.0);
//	          fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
//	            @Override public void handle(ActionEvent actionEvent) {
//	              initStage.hide();
//	            }
//	          });
//	          fadeSplash.play();
//	        }
//	      }
//	    });
	  }


	public void openWindow(String title, String url, double width, double height) {
		mainStage = new Stage();
		Pane root = new Pane();
		mainStage.setTitle(title);
		mainStage.setIconified(true);
		webEngine.load(url);
		loadProgress.progressProperty().bind(browser.getEngine().getLoadWorker().workDoneProperty().divide(100));
		root.getChildren().add(browser);
		Scene scene = new Scene(root, width, height);
		browser.prefWidthProperty().bind(scene.widthProperty());
		browser.prefHeightProperty().bind(scene.heightProperty());
		mainStage.setScene(scene);
		mainStage.show();
	}

	private void showSplash(Stage initStage) {
		Scene splashScene = new Scene(loadingPane);
		initStage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 800 / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 600 / 2);
		initStage.show();
	}
}
