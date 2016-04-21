package auth_environment.view.Workspaces;

import java.util.List;
import java.util.ResourceBundle;
import game_engine.game_elements.Branch;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.PathTabModel;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PathTab implements IWorkspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory(); 
	
	private BorderPane myBorderPane = new BorderPane(); 
	
	private TextField myPathWidthField;
	
	private Pane canvasPane = new Pane(); 
	
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
		center.getChildren().addAll(this.buildTextInput(),
				this.buildSubmitBranchButton(),
				this.buildMainCanvas()); 
		return center; 
	}
	
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
	
	private void addClickHandlers(Canvas canvas) {
		 canvas.setOnMouseClicked(e -> {
	        	this.myModel.addPosition(e.getX(), e.getY());
	        	this.checkPoint(e.getX(), e.getY());
//	        	this.printCurrentPoints();
//	        	canvasPane.getChildren().add(this.displayPoint(e.getX(), e.getY()));
	        });
	}
	
	private void printPoint(double x, double y) {
    	System.out.println(x + " " + y);
	}
	
	private void printCurrentPoints() {
		this.myModel.printCurrentPositions();
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
