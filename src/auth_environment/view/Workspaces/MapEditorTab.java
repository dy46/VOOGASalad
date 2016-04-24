package auth_environment.view.Workspaces;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.GlobalGameTabModel;
import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IGlobalGameTabModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.FileChooserDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.view.RecTile;
import auth_environment.view.UnitPicker;
import game_engine.game_elements.Unit;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MapEditorTab implements IWorkspace{	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	private BorderPane myBorderPane = new BorderPane(); 
	private TitledPane myMapPane;
	private Canvas myCanvas;
	private UnitPicker myPicker;

	private MapEditorTabModel myModel;
	private List<Unit> myTerrains;
	private List<UnitView> myUnitViewList;
	private IAuthModel myAuthModel;
	private IAuthEnvironment myAuth;
	
	public MapEditorTab(IAuthModel auth) {
		this.myAuthModel = auth;
		this.myAuth = auth.getIAuthEnvironment();
		this.myModel = new MapEditorTabModel(myAuth); 
		
		this.buildTerrainChooser();
		this.buildMapPane();
		this.setupBorderPane();
	}

	private void setupBorderPane() {
		
		this.myBorderPane.setOnMouseEntered(e-> this.refresh());
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		
		this.myBorderPane.setRight(myPicker.getRoot());
		this.myBorderPane.setLeft(myMapPane);
	}
	
	private void refresh(){
		this.myAuth = myAuthModel.getIAuthEnvironment();
		this.myModel = new MapEditorTabModel(myAuthModel.getIAuthEnvironment());
	}
	
	public void buildUnitViewList(){
		myUnitViewList = new ArrayList<UnitView>();
		for(Unit uv:myModel.getTerrains()){
			myUnitViewList.add(new UnitView(uv));
		}
	}
	
	public void buildTerrainChooser(){
		buildUnitViewList();
		myPicker = new UnitPicker();
		if(!myUnitViewList.equals(null)){
			myPicker.init(myUnitViewList);
		}
		myPicker.setTitle("Terrains");
	}
	
	private void buildMapPane(){
		myMapPane = new TitledPane();
        myCanvas = new Canvas(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
        		Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
        this.addClickHandlers(myCanvas);
        this.myMapPane.setContent(myCanvas); 
	}
	
	private void addClickHandlers(Canvas canvas) {
		 canvas.setOnMouseClicked(e -> {
	        	
	        });
	}
	
	private void convertView(){
		
	}
	@Override
	public Node getRoot() {
		// TODO Auto-generated method stub
		return myBorderPane;
	}

}
