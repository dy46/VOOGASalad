package auth_environment.view.tabs;

import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelWaveTab implements IWorkspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private IAuthModel myAuthModel;
	
	private NodeFactory myNodeFactory; 
	private BorderPane myRoot; 
	
	public LevelWaveTab(IAuthModel authModel) {
		this.myAuthModel = authModel;
		this.init();
	}
	
	private void init() {
		this.myRoot = new BorderPane(); 
	}
	
	private Node setupTop() {
		HBox hb = this.myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding"))); 
//		hb.getChildren().add(e)
	}
	
	public Node setupLevels() {
		VBox vb = this.myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		return null; 
	}

	@Override
	public Node getRoot() {
		return this.myRoot;
	}
	
	

}
