package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.game_elements.Branch;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.dialogs.ConfirmationDialog;
import auth_environment.view.BoundLine;
import auth_environment.view.PathPoint;
import auth_environment.view.UnitPicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PathTab extends Tab implements IWorkspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private NodeFactory myNodeFactory;

	private BorderPane myBorderPane;
	private TextField myPathWidthField;
	private Pane canvasPane;
	private ComboBox<String> levelComboBox;
	private ComboBox<String> waveComboBox; 
	private UnitPicker myUnitPicker; 

	private IPathTabModel myPathTabModel;
	private IAuthEnvironment myAuth;
	private IAuthModel myAuthModel;

	private int drawingIndex;
	private List<Position> currentBranch;

	public PathTab(String name, IAuthModel auth) {
		super(name); 
		this.myAuthModel = auth;
		this.myAuth = auth.getIAuthEnvironment();
		this.myPathTabModel = new PathTabModel(this.myAuth); 
		this.myBorderPane = new BorderPane(); 
		this.myNodeFactory = new NodeFactory(); 
		this.canvasPane = new Pane(); 
		this.setupBorderPane();
		this.currentBranch = new ArrayList<>();
		this.drawMap();
		this.setContent(this.getRoot());
	}

	private void addConfirmationDialog() {
		String gridHeaderText = "You have the option to have a default grid for extension.";
		String gridContextText = "Do you want this? Cancel if you want to start with a blank slate.";
		boolean confirmation = new ConfirmationDialog().getConfirmation(gridHeaderText, gridContextText);
		if(confirmation){
			this.myPathTabModel.createGrid();
		}
	}

	private void refresh() {
		this.clearComboBoxes();
		this.myAuth = myAuthModel.getIAuthEnvironment();
		this.myPathTabModel.refresh(this.myAuth);
		this.buildLevelComboBox();
		//		this.drawMap();
	}

	private void setupBorderPane() {
		this.setOnSelectionChanged(e -> this.refresh());
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setCenter(this.buildCenter());
		this.myBorderPane.setRight(this.buildRight());
	}

	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));

		center.getChildren().addAll(
				this.buildTextInput(),
				this.canvasPane); 
		this.addClickHandlers();

		return center; 
	}

	private Node buildRight() {
		VBox right = this.myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));

		right.getChildren().addAll(this.buildComboBoxes());
		return right; 
	}

	// Called once, when Tab is first constructed 
	private Node buildComboBoxes() {
		VBox vb = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));

		this.levelComboBox = new ComboBox<String>();
		this.waveComboBox = new ComboBox<String>(); 
		this.myUnitPicker = new UnitPicker("Units to Spawn"); 

		this.buildLevelComboBox();

		vb.getChildren().addAll(this.levelComboBox, this.waveComboBox, this.myUnitPicker.getRoot()); 
		return vb; 
	}

	private void clearComboBoxes() {
		this.levelComboBox.getItems().clear();
		this.waveComboBox.getItems().clear();
	}

	private void buildLevelComboBox() {
		this.levelComboBox.getItems().clear();
		if (!this.myAuthModel.getIAuthEnvironment().getLevels().isEmpty()) {
			this.levelComboBox.getItems().addAll(this.myPathTabModel.getLevelNames());
			this.levelComboBox.setOnAction(event -> {
				String selectedItem = ((ComboBox<String>)event.getSource()).getSelectionModel().getSelectedItem();
				this.buildWaveComboBox(selectedItem);
				event.consume();
			});
		}
		else {
			this.levelComboBox.getItems().add("No Levels Available"); 
		}
	}

	private void buildWaveComboBox(String levelName) {
		this.waveComboBox.getItems().clear();
		System.out.println(levelName);
		if (this.myPathTabModel.getWaveNames(levelName)!=null) {
			this.waveComboBox.getItems().addAll(this.myPathTabModel.getWaveNames(levelName));
			this.waveComboBox.setOnAction(event -> {
				String selectedItem = ((ComboBox<String>)event.getSource()).getSelectionModel().getSelectedItem();
				System.out.println("Wave combo box used " + selectedItem + "!");
				if (selectedItem!=null) {
					this.buildUnitPicker(selectedItem);
				}
				event.consume();
			});
		}
	}

	private void buildUnitPicker(String waveName) {
		this.myUnitPicker.setUnits(this.myPathTabModel.getWaveUnits(waveName));
	}

	private HBox buildTextInput() {
		VBox vb = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));

		HBox hb0 = myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding")));

		HBox hb1 = myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding")));

		// TODO: duplicate code with GlobalGameTab
		this.myPathWidthField = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("pathWidthPrompt"));
		this.myPathWidthField.setOnAction(e -> this.submitPathWidth(this.myPathWidthField));

		Button submitBranchButton = myNodeFactory.buildButton(myNamesBundle.getString("submitBranchButtonLabel"));
		submitBranchButton.setOnAction(e -> this.submitBranch());
		Button drawPathButton = myNodeFactory.buildButton(myNamesBundle.getString("drawPath"));
		drawPathButton.setOnAction(e -> this.updateDrawIndex(0));
		Button drawGoalButton = myNodeFactory.buildButton(myNamesBundle.getString("drawGoal"));
		drawGoalButton.setOnAction(e -> this.updateDrawIndex(1));
		Button drawSpawnButton = myNodeFactory.buildButton(myNamesBundle.getString("drawSpawn"));
		drawSpawnButton.setOnAction(e -> this.updateDrawIndex(2));

		hb0.getChildren().addAll(this.myPathWidthField, 
				submitBranchButton);
		hb1.getChildren().addAll(drawPathButton, drawGoalButton, drawSpawnButton);
		vb.getChildren().addAll(hb0, hb1);

		return this.myNodeFactory.centerNode(vb); 
	}

	private void updateDrawIndex(int index) {
		this.drawingIndex = index;
	}

	// TODO: make this protected in an abstract class 
	private void submitPathWidth(TextField input) {
		if (checkValidInput(input)) {
			this.myPathTabModel.setPathWidth(Double.parseDouble(input.getText()));
			input.clear();
		}
	}

	private void submitBranch() {
		this.myPathTabModel.submitBranch();
		this.currentBranch.clear();
		this.drawMap();
	}

	// TODO: make this protected in an abstract class 
	private boolean checkValidInput(TextField input) {
		return input.getText().length() > 0; 
	}

	private void drawBranch(Branch branch) {
		this.displayEndPoint(branch.getFirstPosition());
		this.displayEndPoint(branch.getLastPosition());
		Position lastPosDrawn = branch.getFirstPosition();
		for(Position currPos : branch.getPositions()){
			this.addBoundLine(lastPosDrawn.getX(), 
					lastPosDrawn.getY(), 
					currPos.getX(), 
					currPos.getY(),
					branch);
			lastPosDrawn = currPos;
			displayPoint(currPos);
		}
	}

	private void clearMap(){
		canvasPane.getChildren().clear();
	}

	private void drawMap() {
		clearMap();
		drawBranches();
		drawSpawns();
		drawGoals();
		drawCurrentBranch();
	}

	private void drawBranches(){
		this.myPathTabModel.getEngineBranches().stream().forEach(b -> this.drawBranch(b));
	}

	private void drawSpawns() {
		this.myPathTabModel.getSpawns().forEach(s -> this.displaySpawnPoint(s));
	}

	private void drawGoals() {
		this.myPathTabModel.getGoals().forEach(s -> this.displayGoalPoint(s));
	}

	private void addBoundLine(double startX, double startY, double endX, double endY, Branch branch) {
		BoundLine b = new BoundLine(new SimpleDoubleProperty(startX),
				new SimpleDoubleProperty(startY),
				new SimpleDoubleProperty(endX),
				new SimpleDoubleProperty(endY));
		this.myPathTabModel.saveBranch(b, branch);
		b.setOnMouseClicked(e -> this.myPathTabModel.reselectBranch(b)); 
		this.canvasPane.getChildren().add(b); 
	}

	private void addClickHandlers() {
		this.canvasPane.setOnMouseClicked(e -> {
			if (e.isControlDown()) {
				if(drawingIndex == 0){
					this.addPosition(e.getX(), e.getY());
					this.currentBranch.add(new Position(e.getX(), e.getY()));
				}
				else if (drawingIndex == 1){
					addGoalPoint(e.getX(), e.getY());
				}
				else if (drawingIndex == 2){
					addSpawnPoint(e.getX(), e.getY());
				}
				this.drawMap();
			}
		});
	}

	private void drawCurrentBranch() {
		this.currentBranch.forEach(p -> displayClickedPoint(p));
	}

	private void addPosition(double x, double y) {
		this.myPathTabModel.addNewPosition(x, y);
	}

	private void addSpawnPoint(double x, double y){
		this.myPathTabModel.addNewSpawn(x, y);
	}

	private void addGoalPoint(double x, double y){
		this.myPathTabModel.addNewGoal(x, y);
	}

	// TODO: extract constants
	private void displayEndPoint(Position p) {
		PathPoint point = new PathPoint(p, this.myPathTabModel.getPathWidth()); 
		point.getCircle().setStroke(Color.BLACK);
		point.getCircle().setFill(Color.GREY.deriveColor(1, 1, 1, 0.7));
		point.getCircle().setOnMouseClicked(e -> {
			if(e.getButton().equals(MouseButton.PRIMARY)){
				if(e.getClickCount() == 2){
					this.displayClickedPoint(p);
					this.addPosition(point.getPosition().getX(), point.getPosition().getY());
					this.currentBranch.add(point.getPosition());
				}
			}
		});
		this.canvasPane.getChildren().add(point.getCircle());
	}

	private void displayClickedPoint(Position p) {
		PathPoint point = new PathPoint(p, this.myPathTabModel.getPathWidth()); 
		point.getCircle().setStroke(Color.BLACK);
		point.getCircle().setFill(Color.RED);
		this.canvasPane.getChildren().add(point.getCircle());
	}

	private void displaySpawnPoint(Position spawn) {
		PathPoint point = new PathPoint(spawn, this.myPathTabModel.getPathWidth()); 
		point.getCircle().setStroke(Color.BLACK);
		point.getCircle().setFill(Color.BLUE);
		setUpNodeTarget(point, this.myUnitPicker, this.myPathTabModel);
		this.canvasPane.getChildren().add(point.getCircle());
	}

	private void displayGoalPoint(Position goal) {
		PathPoint point = new PathPoint(goal, this.myPathTabModel.getPathWidth()); 
		point.getCircle().setStroke(Color.BLACK);
		point.getCircle().setFill(Color.GREEN);
		point.getCircle().setOnMouseClicked(e -> {
			if(e.getButton().equals(MouseButton.PRIMARY)){
				if(e.getClickCount() == 2){
					this.myPathTabModel.addGoalToActiveLevel(point.getPosition());
				}
			}
		});
		this.canvasPane.getChildren().add(point.getCircle());
	}

	private void displayPoint(Position p) {
		PathPoint point = new PathPoint(p, 1.0);
		point.getCircle().setStroke(Color.BLACK);
		canvasPane.getChildren().add(point.getCircle()); 
	}

	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

	public void setUpNodeTarget(PathPoint pathPoint, UnitPicker picker, IPathTabModel pathModel) {
		Circle target = pathPoint.getCircle(); 

		target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				//					System.out.println("Dragging over Node...");
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				event.consume();
			}
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				//					System.out.println("Drag entered...");
				//					if (event.getGestureSource() != target &&
				//							event.getDragboard().hasString()) {
				//					}
				event.consume();
			}
		});

		target.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				//					System.out.println("Drag exited...");
				event.consume();
			}
		});

		target.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.COPY);
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					UnitView uv = ((UnitView)(picker.getRoot().lookup("#" + db.getString())));
					pathModel.setActiveUnit(uv.getUnit());
					Position pos = pathPoint.getPosition(); 
					pathModel.addSpawnToActiveLevel(pos);
					uv.getUnit().getProperties().setMovement(new Movement(pos));
					uv.getUnit().getProperties().setPosition(pos);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

}