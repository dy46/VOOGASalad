package auth_environment.delegatesAndFactories;

import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import javafx.geometry.Pos;
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
import javafx.stage.Stage;

public class BrowserWindowDelegate {

	private static final String NAMES_PACKAGE = "auth_environment/resources/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private Pane loadingPane;
	private ProgressBar loadProgress;
	private Label progressText;

	private WebView browser = new WebView();
	private WebEngine webEngine = browser.getEngine();

	public BrowserWindowDelegate() {
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

	private void addLoadingScreen(WebView webView) {
		webView.getEngine().documentProperty().addListener(new ChangeListener<Document>() {
			@Override public void changed(ObservableValue<? extends Document> observableValue, Document document, Document document1) {
				if (initStage.isShowing()) {
					loadProgress.progressProperty().unbind();
					loadProgress.setProgress(1);
					progressText.setText("All hobbits are full.");
					mainStage.setIconified(false);
					initStage.toFront();
					FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
					fadeSplash.setFromValue(1.0);
					fadeSplash.setToValue(0.0);
					fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent actionEvent) {
							initStage.hide();
						}
					});
					fadeSplash.play();
				}
			}
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
		Scene splashScene = new Scene(loadingPane);
		initStage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		initStage.show();
	}
}
