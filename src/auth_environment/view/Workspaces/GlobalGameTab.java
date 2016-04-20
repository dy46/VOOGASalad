package auth_environment.view.Workspaces;

import java.io.File;
import java.util.ResourceBundle;

import auth_environment.Models.GlobalGameTabModel;
import auth_environment.Models.Interfaces.IGlobalGameTabModel;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import game_engine.IAuthInterface;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

public class GlobalGameTab implements IWorkspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	private BorderPane myBorderPane = new BorderPane(); 
	private ImageView mySplashPreview; 
	private TextField myGameNameField;
	
	private IGlobalGameTabModel myModel;
	
	public GlobalGameTab(IAuthInterface auth) {
		this.setupBorderPane();
		this.myModel = new GlobalGameTabModel(auth); 
	}

	private void setupBorderPane() {

		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		
		this.myBorderPane.setCenter(this.buildCenter());
		
	}
	
	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		center.getChildren().addAll(this.buildWompImage(),
				myNodeFactory.centerNode(this.buildTextInput()),
				myNodeFactory.centerNode(this.buildSplashChooser()));
		return center; 
	}
	
	private HBox buildWompImage() {
		return myNodeFactory.centerNode(myNodeFactory.buildImageView(myNamesBundle.getString("wompWelcomeImage")));
	}
	
	private HBox buildTextInput() {
		this.myGameNameField = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
		this.myGameNameField.setOnAction(e -> this.submitButtonPressed(this.myGameNameField));
		
		Button submitNameButton = myNodeFactory.buildButton(myNamesBundle.getString("submitButtonLabel"));
		submitNameButton.setOnAction(e -> this.submitButtonPressed(this.myGameNameField));
		
		HBox hb = myNodeFactory.centerNode(this.myGameNameField);
		hb.getChildren().add(submitNameButton);
		return hb;
	}
	
	// TODO: set spacing/padding 
	// TODO: resize ImageView 
	private HBox buildSplashChooser() {
		mySplashPreview = myNodeFactory.buildImageView(myURLSBundle.getString("placeholderImage"),
				Double.parseDouble(myDimensionsBundle.getString("splashPreviewWidth")),
				Double.parseDouble(myDimensionsBundle.getString("splashPreviewHeight")));
		Button splashButton = myNodeFactory.buildButton(myNamesBundle.getString("chooseSplashLabel"));
		splashButton.setOnAction(e -> this.buildSplashChooser());
		HBox hb = myNodeFactory.centerNode(mySplashPreview);
		hb.getChildren().add(splashButton); 
		return hb; 
	}

	//	public void writeToGameData() {
	////		gameData.setLevels(myPicker.getLevels());
	//		gameData.setEnemies(myPicker.getEnemies());
	//		gameData.setTerrains(myPicker.getTerrains());
	//		gameData.setTowerTypes(myPicker.getTowers());
	//		gameData.setPaths(myDisplay.getGrid().getPathGraphFactory().getPaths());
	//	}
	
	// TODO: exctract these methods to the GlobalGameModel class
	private void submitButtonPressed(TextField input) {
		if (checkValidInput(input)) {
			this.myModel.setGameName(input.getText());
			input.clear();
		}
	}
	
	// TODO: put in Model
	private void chooseSplash() {
		FileChooserDelegate fileChooser = new FileChooserDelegate(); 
		File splash = fileChooser.chooseImage(myNamesBundle.getString("chooseSplashLabel"));
		// TODO: display the image in an ImageView
		
		
		// TODO: store the image name
		System.out.println(splash.getName());
		this.myModel.setSplashFile(splash.getName());
		
		// TODO: save the file to Game_Images (ask Virginia) 
		
	}
	
	private boolean checkValidInput(TextField input) {
		return input.getText().length() > 0; 
	}
	
	@Override
	public Node getRoot() {
		return this.myBorderPane; 
	}
}