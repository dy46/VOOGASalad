package auth_environment.view.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import auth_environment.dialogs.ConfirmationDialog;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class PathTab implements IWorkspace {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private NodeFactory myNodeFactory;

	private BorderPane myBorderPane;
	private TextField myPathWidthField;
	private Pane canvasPane;

	private IPathTabModel myModel;
	private IAuthEnvironment myAuth;
	private IAuthModel myAuthModel;

	private int drawingIndex;
	private List<Position> currentBranch;

	public PathTab(IAuthModel auth) {
		this.myAuthModel = auth;
		this.myAuth = auth.getIAuthEnvironment();
		this.myModel = new PathTabModel(this.myAuth); 
		this.myBorderPane = new BorderPane(); 
		this.myNodeFactory = new NodeFactory(); 
		this.canvasPane = new Pane(); 
		this.setupBorderPane();
//		String gridHeaderText = "You have the option to have a default grid for extension.";
//		String gridContextText = "Do you want this? Cancel if you want to start with a blank slate.";
//		boolean confirmation = new ConfirmationDialog().getConfirmation(gridHeaderText, gridContextText);
//		if(confirmation){
//			this.myModel.createGrid();
//		}
		currentBranch = new ArrayList<>();
		this.drawMap();
	}

	private void refresh() {
		this.myAuth = myAuthModel.getIAuthEnvironment();
		this.myModel = new PathTabModel(myAuthModel.getIAuthEnvironment()); 
		this.drawMap();
		this.drawCurrentBranch();
	}

	private void setupBorderPane() {
		this.myBorderPane.setOnMouseEntered(e -> this.refresh());
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setCenter(this.buildCenter());
	}

	private Node buildCenter() {
		VBox center = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")), 
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding")));
		center.getChildren().addAll(this.buildTextInput(),
				this.buildMainCanvas()); 
		return center; 
	}

	// TODO: remove after testing
	//	private UnitView buildTestUnitView() {
	//		TestingEngineWorkspace test = new TestingEngineWorkspace();
	//		test.setUpEngine(1.0);
	//		Unit tower = test.getTerrains().get(0); 
	//		UnitView uv = new UnitView(tower, "smackCat.gif"); 
	//		return uv; 
	//	}

	private HBox buildTextInput() {
		// TODO: duplicate code with GlobalGameTab
		this.myPathWidthField = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("pathWidthPrompt"));
		this.myPathWidthField.setOnAction(e -> this.submitPathWidth(this.myPathWidthField));

		Button submitNameButton = myNodeFactory.buildButton(myNamesBundle.getString("submitButtonLabel"));
		submitNameButton.setOnAction(e -> this.submitPathWidth(this.myPathWidthField));

		Button drawPathButton = myNodeFactory.buildButton(myNamesBundle.getString("drawPath"));
		drawPathButton.setOnAction(e -> drawingIndex = 0);
		Button drawGoalButton = myNodeFactory.buildButton(myNamesBundle.getString("drawGoal"));
		drawPathButton.setOnAction(e -> drawingIndex = 1);
		Button drawSpawnButton = myNodeFactory.buildButton(myNamesBundle.getString("drawSpawn"));
		drawPathButton.setOnAction(e -> drawingIndex = 2);

		Button submitBranchButton = myNodeFactory.buildButton(myNamesBundle.getString("submitBranchButtonLabel"));
		submitBranchButton.setOnAction(e -> this.submitBranch());

		HBox hb = myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding")));
		hb.getChildren().addAll(this.myPathWidthField, submitNameButton, submitBranchButton, drawPathButton, drawGoalButton, drawSpawnButton);
		return this.myNodeFactory.centerNode(hb); 
	}

	// TODO: make this protected in an abstract class 
	private void submitPathWidth(TextField input) {
		if (checkValidInput(input)) {
			this.myModel.setPathWidth(Double.parseDouble(input.getText()));
			input.clear();
		}
	}

	private void submitBranch(){
		this.myModel.submitBranch();
		this.currentBranch.clear();
		this.drawMap();
	}

	// TODO: make this protected in an abstract class 
	private boolean checkValidInput(TextField input) {
		return input.getText().length() > 0; 
	}

	private void drawBranch(Branch branch) {
		this.displayEndPoint(branch.getFirstPosition().getX(),
				branch.getFirstPosition().getY());
		this.displayEndPoint(branch.getLastPosition().getX(),
				branch.getLastPosition().getY());
		Position lastPosDrawn = branch.getFirstPosition();
		for(Position currPos : branch.getPositions()){
			this.addBoundLine(lastPosDrawn.getX(), 
					lastPosDrawn.getY(), 
					currPos.getX(), 
					currPos.getY());
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
		this.myModel.getEngineBranches().stream().forEach(b -> this.drawBranch(b));
	}

	private void drawSpawns() {
		this.myModel.getSpawns().forEach(s -> this.displaySpawnPoint(s));
	}

	private void drawGoals() {
		this.myModel.getGoals().forEach(s -> this.displayGoalPoint(s));
	}

	private Node buildMainCanvas() {
		Canvas canvas = new Canvas(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
				Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
		this.addClickHandlers(canvas);
		this.canvasPane.getChildren().add(canvas); 
		return canvasPane; 
	}

	private void addBoundLine(double startX, double startY, double endX, double endY) {
		this.canvasPane.getChildren().add(new BoundLine(new SimpleDoubleProperty(startX),
				new SimpleDoubleProperty(startY),
				new SimpleDoubleProperty(endX),
				new SimpleDoubleProperty(endY)
				));
	}

	// Source: http://stackoverflow.com/questions/19748744/javafx-how-to-connect-two-nodes-by-a-line
	class BoundLine extends Line {
		BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
			startXProperty().bind(startX);
			startYProperty().bind(startY);
			endXProperty().bind(endX);
			endYProperty().bind(endY);
			setStrokeWidth(2);
			setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
			setStrokeLineCap(StrokeLineCap.BUTT);
			getStrokeDashArray().setAll(10.0, 5.0);
			setMouseTransparent(true);
		}
	}

	private void addClickHandlers(Canvas canvas) {
		this.canvasPane.setOnMouseClicked(e -> {
			//		canvas.setOnMouseClicked(e -> {
			if(drawingIndex == 0){
				this.addPosition(e.getX(), e.getY());
				this.currentBranch.add(new Position(e.getX(), e.getY()));
			}
			else if (drawingIndex == 1){
				addSpawnPoint(e.getX(), e.getY());
			}
			else{
				addGoalPoint(e.getX(), e.getY());
			}
			this.drawMap();
		});
	}

	private void drawCurrentBranch(){
		this.currentBranch.forEach(p -> displayClickedPoint(p));
	}

	private void addPosition(double x, double y) {
		this.myModel.addNewPosition(x, y);
	}

	private void addSpawnPoint(double x, double y){
		this.myModel.addNewSpawn(x, y);
	}

	private void addGoalPoint(double x, double y){
		this.myModel.addNewGoal(x, y);
	}

	// TODO: extract constants
	private void displayEndPoint(double x, double y) {
		Circle circle = new Circle(this.myModel.getPathWidth());
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.GREY.deriveColor(1, 1, 1, 0.7));
		circle.relocate(x - circle.getRadius(), y - circle.getRadius());
		this.canvasPane.getChildren().add(circle); 
	}

	private void displayClickedPoint(Position p){
		Circle circle = new Circle(this.myModel.getPathWidth());
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.RED);
		circle.relocate(p.getX()- circle.getRadius(), p.getY()- circle.getRadius());
		this.canvasPane.getChildren().add(circle);
	}

	private void displaySpawnPoint(Position spawn) {
		Circle circle = new Circle(this.myModel.getPathWidth());
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.BLUE);
		circle.relocate(spawn.getX()- circle.getRadius(), spawn.getY()- circle.getRadius());
		this.canvasPane.getChildren().add(circle);
	}

	private void displayGoalPoint(Position goal) {
		Circle circle = new Circle(this.myModel.getPathWidth());
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.GREEN);
		circle.relocate(goal.getX()- circle.getRadius(), goal.getY()- circle.getRadius());
		this.canvasPane.getChildren().add(circle); 
	}

	private void displayPoint(Position p){
		Circle circle = new Circle(1);
		circle.setStroke(Color.BLACK);
		circle.relocate(p.getX(), p.getY());
		this.canvasPane.getChildren().add(circle); 
	}

	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}