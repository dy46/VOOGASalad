package auth_environment.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/6/16.
 * 
 * Team member responsible: Brian
 * 
 * First (interactive) screen displayed to the Developer. Asks for Game name. 
 * 
 * TODO: Ask Austin to store (and pass around) the Game Name
 */

public class Welcome {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory();
	private TextField gameNameInput;
	private Stage myStage = new Stage(); 
	private Scene welcomeScene; 
	private VBox myRoot;
	private View myView; 

	public Welcome(View view) {
		this.myView = view; 
		this.init();
	}

	private void init() {
		this.myRoot = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding"))
				);
		this.welcomeScene = new Scene(this.myRoot);
		this.myRoot.getChildren().addAll(this.buildWompImage(), 
				this.buildTextInput(), 
				this.buildSubmitButton(),
				this.buildAnimation()
				);
		this.myRoot.getChildren().addAll(this.testDraggable());
		this.myRoot.setStyle("-fx-background-color: #292929;");
		this.myRoot.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.welcomeScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet"));
		this.myStage.setScene(this.welcomeScene);
		this.myStage.show();
		this.myStage.toFront();
	}

	private HBox buildWompImage() {
		return myNodeFactory.centerNode(myNodeFactory.buildImageView(myNamesBundle.getString("wompWelcomeImage")));
	}

	private TextField buildTextInput() {
		this.gameNameInput = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
		this.gameNameInput.setOnAction(e -> this.submitButtonPressed());
		return this.gameNameInput; 
	}

	private HBox buildSubmitButton() {
		Button submit = myNodeFactory.buildButton(myNamesBundle.getString("buildButtonLabel"));
		submit.setOnAction(e -> this.submitButtonPressed());
		return myNodeFactory.centerNode(submit);
	}
	
	private ImageView buildAnimation() {
		ImageView animation = myNodeFactory.buildImageView(myNamesBundle.getString("wompAnimation"));
		animation.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")));
		return animation;
	}

	private void submitButtonPressed() {
		if (checkValidName()) {
			this.myStage.hide();
			this.myView.display();
			// TODO: save entered name somewhere... ask Austin
		}
	}

	private boolean checkValidName() {
		return this.gameNameInput.getText().length() > 0; 
	}
}
