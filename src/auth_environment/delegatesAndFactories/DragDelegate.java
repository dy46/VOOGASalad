package auth_environment.delegatesAndFactories;

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
	
//	private static final DataFormat gameElementFormat = new DataFormat("Game Element"); // need help extracting  
			
	public DragDelegate() {}
	
	public void setupSource(Tile source) {
		source.getShape().setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = source.getShape().startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
//				content.put(DragDelegate.gameElementFormat, source.getElement());
				content.putString(source.getName());
				db.setContent(content);
				event.consume();
			}
		});
		
		source.getShape().setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
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
				if (event.getGestureSource() != target &&
						event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		target.getShape().setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != target &&
						event.getDragboard().hasString()) {
//					target.showCurrentElement();
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
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
//					target.updateElement( (GameElement) (db.getContent(DragDelegate.gameElementFormat)) ); 
					target.setName(db.getString());
					target.placeCurrentElement(); 
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
}
