package auth_environment.view.Workspaces;

import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	
	public PathTab(IAuthEnvironment auth) {
		this.myModel = new PathTabModel(auth); 
		this.setupBorderPane();
	}
	
	private void setupBorderPane() {

		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		
		this.myBorderPane.setCenter(this.buildCenter());
		
	}
	
	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		center.getChildren().addAll(this.buildMainCanvas()); 
		return center; 
	}
	
	private Node buildMainCanvas() {
        Canvas canvas = new Canvas(600, 400); 
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {      
                    	System.out.println(t.getX() + " " + t.getY());
                    }
                });
        return canvas; 
	}
	
	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}
