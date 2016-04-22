package auth_environment.delegatesAndFactories;

import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IUnitView;
import auth_environment.view.Tile;
import game_engine.game_elements.GameElement;
import game_engine.game_elements.Unit;
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
	
	private static final DataFormat unitViewFormat = new DataFormat("UnitView"); // need help extracting  
			
	public DragDelegate() {
		
	}

	public void addUnitViewSource(UnitView source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.put(DragDelegate.unitViewFormat, source.getUnit());
//				content.putString(source.getUnit().getImgName());
				db.setContent(content);
				event.consume();
			}
		});
	
		source.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.MOVE) {
					System.out.println("Drag completed for source"); 
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
					System.out.println("Name: " + db.getString());
					target.setName(db.getString());
					target.placeCurrentElement(); 
					success = true;
					System.out.println("hi");
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
		
		
	}
}
