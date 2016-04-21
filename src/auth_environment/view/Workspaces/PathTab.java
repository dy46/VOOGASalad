package auth_environment.view.Workspaces;

import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
        Canvas canvas = createCanvasGrid(600, 300, true);
        return canvas; 
	}
	
	   private Canvas createCanvasGrid(int width, int height, boolean sharp) {
	        Canvas canvas = new Canvas(width, height);
	        GraphicsContext gc = canvas.getGraphicsContext2D() ;
	        gc.setLineWidth(1.0);
	        for (int x = 0; x < width; x+=10) {
	            double x1 ;
	            if (sharp) {
	                x1 = x + 0.5 ;
	            } else {
	                x1 = x ;
	            }
	            gc.moveTo(x1, 0);
	            gc.lineTo(x1, height);
	            gc.stroke();
	        }

	        for (int y = 0; y < height; y+=10) {
	            double y1 ;
	            if (sharp) {
	                y1 = y + 0.5 ;
	            } else {
	                y1 = y ;
	            }
	            gc.moveTo(0, y1);
	            gc.lineTo(width, y1);
	            gc.stroke();
	        }
	        return canvas ;
	    }

	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}
