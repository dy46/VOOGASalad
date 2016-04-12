package auth_environment.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.delegatesAndFactories.NodeFactory;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/6/16.
 * 
 * Team member responsible: Brian
 * 
 * First (interactive) screen displayed to the Developer. Asks for Game name. 
 * 
 * TODO: Ask Austin to store (and pass around) the Game Name
 */

public class Welcome {

	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);

	private NodeFactory myNodeFactory = new NodeFactory();
	private TextField gameNameInput;
	private Stage myStage = new Stage(); 
	private Scene welcomeScene; 
	private VBox myRoot;
	private View myView; 

	public Welcome(View view) {
		this.myView = view; 
		this.init();
	}

	private void init() {
		this.myRoot = myNodeFactory.buildVBox(Double.parseDouble(myDimensionsBundle.getString("defaultVBoxSpacing")),
				Double.parseDouble(myDimensionsBundle.getString("defaultVBoxPadding"))
				);
		this.welcomeScene = new Scene(this.myRoot);
		this.myRoot.getChildren().addAll(this.buildWompImage(), 
				this.buildTextInput(), 
				this.buildSubmitButton(),
				this.buildAnimation()
				);
		this.myRoot.getChildren().addAll(this.testDraggable());
		this.myRoot.setStyle("-fx-background-color: #292929;");
		this.myRoot.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
				Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.welcomeScene.getStylesheets().add(myURLSBundle.getString("darkStylesheet"));
		this.myStage.setScene(this.welcomeScene);
		this.myStage.show();
		this.myStage.toFront();
	}

	private HBox buildWompImage() {
		return myNodeFactory.centerNode(myNodeFactory.buildImageView(myNamesBundle.getString("wompWelcomeImage")));
	}

	private TextField buildTextInput() {
		this.gameNameInput = myNodeFactory.buildTextFieldWithPrompt(myNamesBundle.getString("gameNamePrompt"));
		this.gameNameInput.setOnAction(e -> this.submitButtonPressed());
		return this.gameNameInput; 
	}

	private HBox buildSubmitButton() {
		Button submit = myNodeFactory.buildButton(myNamesBundle.getString("buildButtonLabel"));
		submit.setOnAction(e -> this.submitButtonPressed());
		return myNodeFactory.centerNode(submit);
	}
	
	private Collection<Node> testDraggable() {
		
		Text source = new Text(50, 100, "DRAG ME");
		Text target = new Text(300, 100, "DROP HERE");
		
		List<Node> out = new ArrayList<Node>();
		out.add(source);
		out.add(target);
		
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(source.getText());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
		
		target.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target &&
		                event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		target.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target &&
		                 event.getDragboard().hasString()) {
		             target.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        target.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           target.setText(db.getString());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		source.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		            source.setText("");
		        }
		        event.consume();
		    }
		});
		
		return out;
	}
	
	private ImageView buildAnimation() {
		ImageView animation = myNodeFactory.buildImageView(myNamesBundle.getString("wompAnimation"));
		animation.setFitWidth(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")));
		return animation;
	}

	private void submitButtonPressed() {
		if (checkValidName()) {
			this.myStage.hide();
			this.myView.display();
			// TODO: save entered name somewhere... ask Austin
		}
	}

	private boolean checkValidName() {
		return this.gameNameInput.getText().length() > 0; 
	}
}
