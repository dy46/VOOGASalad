package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/6/16.
 * 
 * Team member responsible: Brian
 * 
 * First (interactive) screen displayed to the Developer. Asks for Game name. 
 */

public class Welcome {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private NodeFactory myNodeFactory = new NodeFactory();
	private Stage mainStage; 
	private Scene welcomeScene; 
	private VBox myRoot = new VBox(); 
	
	public Welcome(Stage stage) {
		this.mainStage = stage; 
		this.myRoot.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
								Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myRoot = this.addWompImage();
		this.myRoot.setStyle("-fx-background-color: #292929;");
		this.welcomeScene = new Scene(this.myRoot);
//		this.welcomeScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet"));
	}
	
	private VBox addWompImage() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")),
											  Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding"))
											  );
		TextField gameNameInput = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
		ImageView womp = myNodeFactory.buildImageView(myNamesBundle.getString("wompWelcomeImage"));
		womp.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth"))/2);
		womp.setFitHeight(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight"))/2);
		center.getChildren().addAll(womp, gameNameInput);
		return center; 
	}
	
	public Node getRoot() {
		return this.myRoot;
	}
	
	public Scene showScene() {
		return this.welcomeScene;
	}

}
