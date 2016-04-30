package auth_environment.view.tabs;

import java.io.File;
import java.util.ResourceBundle;

import auth_environment.Models.GameSettingsTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IGameSettingsTabModel;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.IMainView;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

public class GameSettingsTab extends Tab implements IWorkspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	
	private NodeFactory myNodeFactory;
	private IMainView myMainView; 
	
	private BorderPane myBorderPane;
	private TextField myGameNameField;
	
	private IGameSettingsTabModel myGameSettingsTabModel;
	
	public GameSettingsTab(String name, IAuthModel authModel, IMainView mainView) {
		super(name); 
		myMainView = mainView; 
		myGameSettingsTabModel = new GameSettingsTabModel(authModel); 
		init(); 
	}
	
	private void init() {
		myNodeFactory = new NodeFactory(); 
		setupBorderPane();
	}

	private void setupBorderPane() {
		myBorderPane = new BorderPane(); 
		myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		myBorderPane.setCenter(buildCenter());
		myBorderPane.setBottom(buildBottom());
		setContent(myBorderPane);
	}
	
	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		center.getChildren().addAll(
				buildWompImage(),
				buildTextInput(),
				buildSaveButton(),
				buildLoadButton(),
				buildPlayButton());
		return center; 
	}
	
	private Node buildBottom() {
		HBox bottom = myNodeFactory.buildHBox(10, 10);
		bottom.getChildren().addAll(buildChooseScore(), buildChooseWaveGoal(), buildChoosePlaceValidation());
		return bottom; 
	}
	
	private HBox buildWompImage() {
		return myNodeFactory.centerNode(myNodeFactory.buildImageView(myNamesBundle.getString("wompWelcomeImage")));
	}
	
	private HBox buildTextInput() {
		myGameNameField = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
		myGameNameField.setOnAction(e -> submitButtonPressed(myGameNameField));
		
		Button submitNameButton = myNodeFactory.buildButton(myNamesBundle.getString("submitButtonLabel"));
		submitNameButton.setOnAction(e -> submitButtonPressed(myGameNameField));
		
		HBox hb = myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding")));
		hb.getChildren().addAll(myGameNameField, submitNameButton);
		return myNodeFactory.centerNode(hb); 
	}
	
	private HBox buildSaveButton() {
		Button save = myNodeFactory.buildButton(myNamesBundle.getString("saveItemLabel"));
		save.setOnAction(e -> myGameSettingsTabModel.saveToFile());
		return myNodeFactory.centerNode(save); 
	}
	
	private HBox buildLoadButton() {
		Button load = myNodeFactory.buildButton(myNamesBundle.getString("loadItemLabel"));
		load.setOnAction(e -> myGameSettingsTabModel.loadFromFile());
		return myNodeFactory.centerNode(load); 
	}
	
	private Node buildPlayButton() {
		Button play = myNodeFactory.buildButton(myNamesBundle.getString("playButtonLabel"));
		play.setOnAction(e -> myMainView.displayPlayer());
		return myNodeFactory.centerNode(play); 
	}
	
	private Node buildChooseScore() {
		VBox vb = new VBox(); 
		vb.getChildren().add(myNodeFactory.buildLabel(myNamesBundle.getString("scoreUpdateLabel")));
		ComboBox<String> chooseScore = new ComboBox<String>();
		chooseScore.getItems().addAll(myGameSettingsTabModel.getScoreUpdateNames());
		chooseScore.setOnAction(event -> {
			String selectedItem = ((ComboBox<String>)event.getSource()).getSelectionModel().getSelectedItem();
			myGameSettingsTabModel.chooseScoreUpdate(selectedItem);
			event.consume();
		});
		vb.getChildren().add(chooseScore);  
		return vb;
	}
	
	private Node buildChooseWaveGoal() {
		VBox vb = new VBox(); 
		vb.getChildren().add(myNodeFactory.buildLabel(myNamesBundle.getString("waveGoalLabel")));
		ComboBox<String> chooseWaveGoal = new ComboBox<String>();
		chooseWaveGoal.getItems().addAll(myGameSettingsTabModel.getWaveGoalNames());
		chooseWaveGoal.setOnAction(event -> {
			String selectedItem = ((ComboBox<String>)event.getSource()).getSelectionModel().getSelectedItem();
			myGameSettingsTabModel.chooseWaveGoal(selectedItem);
			event.consume();
		});
		vb.getChildren().add(chooseWaveGoal); 
		return vb; 
	}
	
	private Node buildChoosePlaceValidation() {
		VBox vb = new VBox(); 
		vb.getChildren().add(myNodeFactory.buildLabel(myNamesBundle.getString("placeValidationLabel")));
		ComboBox<String> choosePlaceValidation = new ComboBox<String>(); 
		choosePlaceValidation.getItems().addAll(myGameSettingsTabModel.getPlaceValidationNames());
		choosePlaceValidation.setOnAction(event -> {
			String selectedItem = ((ComboBox<String>)event.getSource()).getSelectionModel().getSelectedItem();
			myGameSettingsTabModel.choosePlaceValidation(selectedItem);
			event.consume();
		});
		vb.getChildren().add(choosePlaceValidation);
		return vb; 
	}
	
	private void submitButtonPressed(TextField input) {
		if (checkValidInput(input)) {
			myGameSettingsTabModel.setGameName(input.getText());
			input.clear();
		}
	}
	
	private boolean checkValidInput(TextField input) {
		return input.getText().length() > 0; 
	}
	
	@Override
	public Node getRoot() {
		return myBorderPane; 
	}
}