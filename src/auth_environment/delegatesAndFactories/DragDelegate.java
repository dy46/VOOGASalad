package auth_environment.delegatesAndFactories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import auth_environment.view.RecTile;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by BrianLin on 4/11/2016
 * Team member responsible: Brian
 *
 * This delegate class adds dragging functions. Woowee dongerino. 
 */

public class DragDelegate {

	public DragDelegate() {

	}
	
	public void setupSource(Text source) {
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
		
		source.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
				/* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
//					source.setText("");
				}
				event.consume();
			}
		});
	}
	
	public void setupTarget(RecTile target) {
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
				target.setFill(Color.WHITE);

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
//					target.setText(db.getString());
					System.out.println("success");
					success = true;
				}
				/* let the source know whether the string was successfully 
				 * transferred and used */
				event.setDropCompleted(success);

				event.consume();
			}
		});
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

		return out;
	}
}
