package auth_environment.view.Workspaces;

import java.util.List;
import java.util.ResourceBundle;

import game_engine.TestingEngineWorkspace;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.delegatesAndFactories.NodeFactory;
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
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory;
	private DragDelegate myDragDelegate; 
	
	private BorderPane myBorderPane;
	
	private TextField myPathWidthField;
	
	private Pane canvasPane;
	private boolean isFirstClick; 
	private double firstX;
	private double firstY;
	
	private IPathTabModel myModel;
	
	public PathTab(IAuthEnvironment auth) {
		this.myModel = new PathTabModel(auth); 
		this.myBorderPane = new BorderPane(); 
		this.myNodeFactory = new NodeFactory(); 
		this.canvasPane = new Pane(); 
		this.myDragDelegate = new DragDelegate(); 
		this.isFirstClick = true;
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
		center.getChildren().addAll(this.buildTextInput(),
				this.buildSubmitBranchButton(),
				this.buildTestUnitView(),
				this.buildMainCanvas()); 
		return center; 
	}
	
	// TODO: remove after testing
	private UnitView buildTestUnitView() {
		TestingEngineWorkspace test = new TestingEngineWorkspace();
		test.setUpEngine(1.0);
		Unit tower = test.getTerrains().get(0); 
		UnitView uv = new UnitView(tower, "smackCat.gif"); 
		this.myDragDelegate.addUnitViewSource(uv);
		return uv; 
	}
	
	// TODO: remove after testing
	private HBox buildSubmitBranchButton() {
		Button button = this.myNodeFactory.buildButton("Submit Branch"); 
		button.setOnAction(a -> this.myModel.submitBranch());
		
		Button clear = this.myNodeFactory.buildButton("Clear"); 
		clear.setOnAction(a -> this.clearCanvas());
		
		Button draw = this.myNodeFactory.buildButton("Draw");
		draw.setOnAction(a -> this.drawBranches(this.myModel.getBranches()));
		
		HBox hb = this.myNodeFactory.buildHBox(10, 10);
		hb.getChildren().addAll(button, clear, draw);
		return this.myNodeFactory.centerNode(hb); 
	}
	
	private void clearCanvas() {
		this.canvasPane.getChildren().clear();
		this.buildMainCanvas();
	}
	
	
	private HBox buildTextInput() {
		// TODO: duplicate code with GlobalGameTab
		this.myPathWidthField = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("pathWidthPrompt"));
		this.myPathWidthField.setOnAction(e -> this.submitPathWidth(this.myPathWidthField));
		
		Button submitNameButton = myNodeFactory.buildButton(myNamesBundle.getString("submitButtonLabel"));
		submitNameButton.setOnAction(e -> this.submitPathWidth(this.myPathWidthField));
		
		HBox hb = myNodeFactory.buildHBox(Double.parseDouble(myDimensionsBundle.getString("defaultHBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultHBoxPadding")));
		hb.getChildren().addAll(this.myPathWidthField, submitNameButton);
		return this.myNodeFactory.centerNode(hb); 
	}
	
	// TODO: make this protected in an abstract class 
	private void submitPathWidth(TextField input) {
		if (checkValidInput(input)) {
			this.myModel.setPathWidth(Double.parseDouble(input.getText()));
			input.clear();
		}
	}
	
	// TODO: make this protected in an abstract class 
	private boolean checkValidInput(TextField input) {
		return input.getText().length() > 0; 
	}
	
	private void drawBranch(Branch branch) {
		this.displayPoint(branch.getFirstPosition().getX(), 
				branch.getFirstPosition().getY());
		this.displayPoint(branch.getLastPosition().getX(),
				branch.getLastPosition().getY());
	}
	
	private void drawBranches(List<Branch> branches) {
		branches.stream().forEach(b -> this.drawBranch(b));
	}
	
	private Node buildMainCanvas() {
        Canvas canvas = new Canvas(Double.parseDouble(this.myDimensionsBundle.getString("canvasWidth")), 
        		Double.parseDouble(this.myDimensionsBundle.getString("canvasHeight")));
        this.addClickHandlers(canvas);
        this.canvasPane.getChildren().add(canvas); 
        return canvasPane; 
	}
	
	private void addDragHandlers(Canvas canvas) {
		canvas.setOnDragDetected(e -> {
			this.setPoint(e.getX(), e.getY());
		});
		canvas.setOnDragOver(e -> System.out.println("drag entered"));
		canvas.setOnDragDropped(e -> {
			System.out.println("dropped");
			this.setPoint(e.getX(), e.getY());
			this.addBoundLine(this.firstX, this.firstY, e.getX(), e.getY());
		});
	}
	
	private void setPoint(double x, double y) {
		this.myModel.addPosition(x, y);
		this.checkPoint(x, y);
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
		 canvas.setOnMouseClicked(e -> {
	        	this.setPoint(e.getX(), e.getY());
	        	if (!this.isFirstClick) {
	        		this.addBoundLine(this.firstX, this.firstY, e.getX(), e.getY());
	        		this.myModel.submitBranch();
	        	}
	        	this.firstX = e.getX();
	        	this.firstY = e.getY();
	        	this.isFirstClick = false; 
	        });
	}
	
	// This is Auth frontend ONLY
	private void displayPoint(double x, double y) {
		Circle circle = new Circle(10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.GREY.deriveColor(1, 1, 1, 0.7));
        circle.relocate(x, y);
        this.canvasPane.getChildren().add(circle); 
	}
	
	private void checkPoint(double x, double y) {
		Circle circle = new Circle(5);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.7));
        circle.relocate(x, y);
        this.canvasPane.getChildren().add(circle); 
	}
	
	@Override
	public Node getRoot() {
		return this.myBorderPane;
	}

}
