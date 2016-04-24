package auth_environment.view.tabs;

import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.AnimationTabModel;
import auth_environment.Models.Interfaces.IAnimationLoaderTabModel;
import auth_environment.Models.Interfaces.IAnimationTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.Workspaces.IWorkspace;
import game_engine.game_elements.Unit;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnimationLoaderTab implements IWorkspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private NodeFactory myNodeFactory;
	
	private BorderPane myBorderPane;
	
	private IAnimationTabModel myModel; 
	
	// TODO: create abstract borderpane tab class
	public AnimationLoaderTab(Unit unit) {
		this.myNodeFactory = new NodeFactory();
		this.myModel = new AnimationTabModel(unit); 
		this.setupBorderPane();
	}
	
	private void setupBorderPane() {
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setCenter(this.buildCenter());
	}
	
	private HBox makedAddImageButton() {
		Button addImage = this.myNodeFactory.buildButton(this.myDimensionsBundle.getString("addImageButton"));
		addImage.setOnAction(e -> this.saveImage());
		
	}
	
	private void saveImage() {
		
	}
	
	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		center.getChildren().addAll(this.makeAddImageButton()); 
		return center; 
	}

	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}
