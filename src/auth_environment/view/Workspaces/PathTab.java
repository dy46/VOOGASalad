package auth_environment.view.Workspaces;

import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class PathTab implements IWorkspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	private BorderPane myBorderPane = new BorderPane(); 
	
	private IPathTabModel myModel;
	
	public PathTab() {
		this.myModel = new PathTabModel(); 
	}
	
	

	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}
