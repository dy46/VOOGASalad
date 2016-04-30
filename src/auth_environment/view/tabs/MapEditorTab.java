package auth_environment.view.tabs;

import java.util.ResourceBundle;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IMapPane;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.GridMapPane;
import auth_environment.delegatesAndFactories.FreeMapPane;
import auth_environment.view.UnitPicker;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class MapEditorTab extends Tab implements IWorkspace {	
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private BorderPane myBorderPane = new BorderPane(); 
	private TitledPane myMapPane;
	private GridMapPane myGridMapPane;
	private FreeMapPane myFreeMapPane;
	
	private UnitPicker myPicker;
	private DragDelegate myDragDelegate;
	private MapEditorTabModel myModel;
	private MapEditorTabModel myGridModel;
	private IAuthModel myAuthModel;
	private IAuthEnvironment myAuth;
	
    private int numCols = 10 ;
    private int numRows = 10 ;
	
	public MapEditorTab(IAuthModel auth, String name) {
		super(name);
		this.myAuthModel = auth;
		this.myAuth = auth.getIAuthEnvironment();
		this.myModel = new MapEditorTabModel(myAuth); 
		this.myDragDelegate = new DragDelegate();
		this.buildTerrainChooser();
		this.buildMapPane();
		this.buildGridMapPane();
		this.buildFreeMapPane();
//		this.updateMapPane(this.myFreeMapPane, "Free");
		this.setupInitialBorderPane();
		this.setContent(this.getRoot());
	}
	
	private void setupInitialBorderPane(){
		this.myBorderPane.setOnMouseEntered(e-> this.refresh());
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setRight(myPicker.getRoot());
		this.myBorderPane.setLeft(buildInitialHBox());
		this.myBorderPane.setBottom(buildHBox());
	}

//	private void setupBorderPane() {
//		this.myBorderPane.setOnMouseEntered(e-> this.refresh());
//		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
//				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
//		this.myBorderPane.setRight(myPicker.getRoot());
//		this.myBorderPane.setLeft(myMapPane);
//		this.myBorderPane.setBottom(buildHBox());
//	}
	
	private void refresh(){
		this.myAuth = myAuthModel.getIAuthEnvironment();
		this.myModel.refresh(this.myAuth);
		System.out.println("Test " + this.myModel.getTerrains());
		this.myPicker.setUnits(this.myModel.getTerrains());
	}
	
	public void buildTerrainChooser(){
		if(this.myModel.getTerrains().equals(null)){
			myPicker = new UnitPicker("Terrains");
		}
		else{
			myPicker = new UnitPicker("Terrains", this.myModel.getTerrains());
		}
	}
	
	private void buildMapPane(){
		myMapPane = new TitledPane();
	}
	
	private void updateMapPane(IMapPane mapPane, String name){
		myMapPane.setText(name);
		myMapPane.setContent(mapPane.getRoot());
		this.myBorderPane.setLeft(myMapPane);
	}
	
	private void buildGridMapPane(){
        myGridMapPane = new GridMapPane(myModel, numCols, numRows);
        myGridMapPane.setPrefSize(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
        		Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
        this.myDragDelegate.setUpNodeTarget(myGridMapPane, myPicker);
	}
	
	private void buildFreeMapPane(){
		myFreeMapPane = new FreeMapPane(myModel);
		myFreeMapPane.setPrefSize(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
				Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
		this.myDragDelegate.setUpNodeTarget(myFreeMapPane, myPicker);
	}
	
    private Button buildClearButton() {
		Button clear = new Button("Clear");
		clear.setOnAction(e -> {
			this.myGridMapPane.getChildren().clear();
			this.myModel.clear();
		});
		return clear;
	}
    
    private CheckBox buildGridSwitchBox(){
    	CheckBox gridSwitch = new CheckBox("Grid Line");
        gridSwitch.setSelected(true);
        gridSwitch.setOnAction(e -> this.myGridMapPane.setGridLinesVisible(!myGridMapPane.getRoot().isGridLinesVisible()));
    	return gridSwitch;
    }
    
    private Button buildGridModeButton(){
    	Button gridMode = new Button("Grid");
    	gridMode.setOnAction( e-> {
    		if(gridMode.getText().equals("Grid")){
    			gridMode.setText("Free");
    			myModel.convert(myGridMapPane);
    			this.updateMapPane(myGridMapPane, "Grid");
    		}else{
    			gridMode.setText("Grid");
    			myModel.convert(myFreeMapPane);
    			this.updateMapPane(myFreeMapPane, "Free");
    		}
    	});
    	
    	return gridMode;
    }
    
	 private HBox buildInitialHBox() {
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(15, 12, 15, 12));
	        hbox.setSpacing(10);

	        Button gridOption = buildGridOptionButton();
	        Button freeOption = buildFreeOptionButton();
	        
	        hbox.getChildren().addAll(gridOption, freeOption);
	        return hbox;
	    }
	 
	 private Button buildGridOptionButton(){
		 Button gridOption = new Button("Grid");
		 gridOption.setOnAction(e -> this.updateMapPane(myGridMapPane, "Grid"));
		 return gridOption;
	 }
	 
	 private Button buildFreeOptionButton(){
		 Button freeOption = new Button("Free");
		 freeOption.setOnAction(e -> this.updateMapPane(myFreeMapPane, "Free"));
		 return freeOption;
	 }
	 
	 
	 
	 private HBox buildHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Button clearButton = buildClearButton();
        CheckBox gridSwitchBox = buildGridSwitchBox();
        Button gridModeButton = buildGridModeButton();
        
        hbox.getChildren().addAll(clearButton,gridSwitchBox, gridModeButton);
        return hbox;
    }
	
	 private void createTextField(HBox hbox){
	    	Label speedlabel = new Label("Columns: ");
	        TextField textField = new TextField();
	        textField.setMaxWidth(50);
	        textField.setPromptText("int");
	        hbox.getChildren().addAll(speedlabel, textField);
	    }
	 
	public Node getRoot() {
		return myBorderPane;
	}

	public Node getFreeMapPane(){
		return this.myFreeMapPane;
	}
}
