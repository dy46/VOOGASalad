package auth_environment.delegatesAndFactories;

import java.util.ResourceBundle;

import auth_environment.view.Tile;
import game_engine.game_elements.GameElement;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * Created by BrianLin on 4/11/2016
 * Team member responsible: Brian
 *
 * This delegate class adds dragging functions. Woowee dongerino. 
 */

public class DragDelegate {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private static final DataFormat gameElementFormat = new DataFormat("Game Element"); // need help extracting  
			
	public DragDelegate() {

	}
	
	
	public void setupSource(Tile source) {
		source.getShape().setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* drag was detected, start a drag-and-drop gesture*/
				/* allow any transfer mode */
				Dragboard db = source.getShape().startDragAndDrop(TransferMode.ANY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.put(DragDelegate.gameElementFormat, source.getElement());
				db.setContent(content);

				event.consume();
			}
		});
		
		source.getShape().setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
				/* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
					System.out.println("Done"); 
				}
				event.consume();
			}
		});
	}
	
	public void setupTarget(Tile target) {
		target.getShape().setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data is dragged over the target */
				/* accept it only if it is not dragged from the same node 
				 * and if it has a string data */
				if (event.getGestureSource() != target &&
						event.getDragboard().hasContent(DragDelegate.gameElementFormat)) {
					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		target.getShape().setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != target &&
						event.getDragboard().hasContent(DragDelegate.gameElementFormat)) {
					target.showCurrentElement();
				}

				event.consume();
			}
		});

		target.getShape().setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				if(!target.hasElement()){
						target.clear();
				}
				event.consume();
			}
		});

		target.getShape().setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasContent(DragDelegate.gameElementFormat)) {
					target.updateElement( (GameElement) (db.getContent(DragDelegate.gameElementFormat)) ); 
					target.placeCurrentElement(); 
					success = true;
				}
				/* let the source know whether the string was successfully 
				 * transferred and used */
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
}
