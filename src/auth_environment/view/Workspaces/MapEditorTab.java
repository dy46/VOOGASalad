package auth_environment.view.Workspaces;

import java.awt.MouseInfo;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Rectangle;

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
	private Pane myCanvasPane;
	private UnitPicker myPicker;
	private ContextMenu myContextMenu;
	
	private MapEditorTabModel myModel;
	private List<Unit> myTerrains;
	private IAuthModel myAuthModel;
	private IAuthEnvironment myAuth;
	
	public MapEditorTab(IAuthModel auth) {
		this.myAuthModel = auth;
		this.myAuth = auth.getIAuthEnvironment();
		this.myModel = new MapEditorTabModel(myAuth); 
		this.myTerrains = myModel.getTerrains();
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
		this.myBorderPane.setBottom(buildClearButton());
	}
	
	private void refresh(){
		this.myAuth = myAuthModel.getIAuthEnvironment();
		this.myModel = new MapEditorTabModel(myAuthModel.getIAuthEnvironment());
	}
	
	public void buildTerrainChooser(){
		if(myTerrains.equals(null)){
			myPicker = new UnitPicker("Terrains");
			System.out.println("WIEOJROIEJTET");
		}
		else{
			myPicker = new UnitPicker("Terrains", myTerrains);
		}
	}
	
	private void buildMapPane(){
		myMapPane = new TitledPane();
//        myCanvas = new Canvas(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
//        		Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
        myCanvasPane = new Pane();
//        myCanvasPane.setGridLinesVisible(true);
//        final int numCols = 10 ;
//        final int numRows = 10 ;
//        for (int i = 0; i < numCols; i++) {
//            ColumnConstraints colConst = new ColumnConstraints();
//            colConst.setPercentWidth(100.0 / numCols);
//            myCanvasPane.getColumnConstraints().add(colConst);
//        }
//        for (int i = 0; i < numRows; i++) {
//            RowConstraints rowConst = new RowConstraints();
//            rowConst.setPercentHeight(100.0 / numRows);
//            myCanvasPane.getRowConstraints().add(rowConst);         
//        }
        myCanvasPane.setPrefSize(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
        		Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
//        myCanvasPane.setScaleX(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")));
//        myCanvasPane.setScaleY(Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
//        myCanvasPane.getChildren().add(myCanvas);
        System.out.println(myCanvasPane);
        setUpNodeTarget(myCanvasPane);
        this.myMapPane.setContent(myCanvasPane); 
	}
	
//To be refactor out
	
    public void setUpNodeTarget(Pane target) {
		
		target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				System.out.println("Dragging over Node...");
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				event.consume();
			}
		});
		
		target.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				System.out.println("Drag entered...");
//				if (event.getGestureSource() != target &&
//						event.getDragboard().hasString()) {
//				}
				event.consume();
			}
		});
		
		target.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				System.out.println("Drag exited...");
				event.consume();
			}
		});
		
		target.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.COPY);
				System.out.println("Drag dropped...");
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
//					System.out.println("Name: " + db.getString());
//					myCanvasPane.getChildren().addAll(new ImageView(db.getImage()));
//					System.out.println(db.getImage());
//					System.out.println(myPicker.getRoot().lookup(db.getString()));
					
					UnitView imv = ((UnitView)(myPicker.getRoot().lookup("#" + db.getString()))).clone();
					imv.setFitHeight(target.getHeight()/10 - 10);
					imv.setFitWidth(target.getWidth()/10 - 10);
					imv.setX(event.getSceneX());
					imv.setY(event.getSceneY() - imv.getFitHeight());
					myModel.addTerrain(event.getSceneX(), event.getSceneY(), imv.getUnit());
					System.out.println("X: " + event.getSceneX());
					System.out.println("Y: " + event.getSceneY());
//					int i = (int)((event.getSceneY()-imv.getFitHeight())/(target.getHeight()/10));
//					int j = (int)(event.getSceneX()/(target.getWidth()/10));
					target.getChildren().add(imv);
//					System.out.println("Grid X: " + i);
//					System.out.println("Grid Y: " + j);
					System.out.println(myPicker.myEditInfo.getChildren());
					imv.addEventHandler(MouseEvent.MOUSE_CLICKED,
						    new EventHandler<MouseEvent>() {
						        @Override public void handle(MouseEvent e) {
						            if (e.getButton() == MouseButton.SECONDARY){ 
						            	myContextMenu = buildContextMenu(imv, target);
						                myContextMenu.show(imv, e.getScreenX(), e.getScreenY());
						            }
						        }
						});
//					imv.setOnMouseClicked(new EventHandler<MouseEvent>(){
//						@Override
//						public void handle(MouseEvent event) {
//							target.getChildren().remove(imv);
//						}
//					});
//					UnitView uv = new UnitView(db.getImage());
//					target.getChildren().addAll(uv);
//					myModel.addTerrain(uv.getX(), uv.getY(), uv.getUnit());
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
    
    private Button buildClearButton() {
		Button clear = new Button("Clear");
		clear.setOnAction(e -> this.myCanvasPane.getChildren().clear());
		return clear;
	}
    
    private ContextMenu buildContextMenu(UnitView imv, Pane tempPane){
    	ContextMenu cm = new ContextMenu();
    	MenuItem cmItem1 = new MenuItem("Delete Image");
    	cmItem1.setOnAction(e-> {tempPane.getChildren().remove(imv);
    	myModel.deleteTerrain(imv.getUnit());
    	});
    	cm.getItems().add(cmItem1);
    	return cm;
    }
    
	@Override
	public Node getRoot() {
		// TODO Auto-generated method stub
		return myBorderPane;
	}

}
