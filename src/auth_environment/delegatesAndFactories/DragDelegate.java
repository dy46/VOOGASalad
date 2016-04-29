package auth_environment.delegatesAndFactories;

import auth_environment.Models.UnitView;
import auth_environment.view.UnitPicker;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

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
	
	

//	public void addUnitViewSource(UnitView source) {
//		source.setOnDragDetected(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent event) {
//				System.out.println("Drag detected..."); 
//				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//				ClipboardContent content = new ClipboardContent();
//				content.putString(Integer.toString(source.getUnit().hashCode())); 
//				db.setContent(content);
//				event.consume();
//			}
//		});
//	
//		source.setOnDragDone(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				if (event.getTransferMode() == TransferMode.MOVE) {
//					System.out.println("Drag completed for source"); 
//				}
//				event.consume();
//			}
//		});
//	}
	
	
	//Set up Source
	public void addUnitViewSource(UnitView source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Drag detected..."); 
				Dragboard db = source.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				source.setId(this.getClass().getSimpleName() + System.currentTimeMillis());
				content.putImage(source.getImage());
				content.putString(source.getId());
				System.out.println(source.getId());
				db.setContent(content);
//				System.out.println("Name: " + db.getImage());
				event.consume();
			}
		});
	
		source.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.COPY) {
					System.out.println("Drag completed for source");
					
				}
				event.consume();
			}
		});
	}
	
	//Set up Target
	
public void setUpNodeTarget(MapPane target, UnitPicker myPicker) {
		
		Pane targetPane = target.getRoot();
		targetPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				System.out.println("Dragging over Node...");
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				event.consume();
			}
		});
		
		targetPane.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				System.out.println("Drag entered...");
				event.consume();
			}
		});
		
		targetPane.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				System.out.println("Drag exited...");
				event.consume();
			}
		});
		
		targetPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.COPY);
				System.out.println("Drag dropped...");
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					System.out.println(db.getString());
					System.out.println("I got this!");
//					System.out.println("Name: " + db.getString());
//					myCanvasPane.getChildren().addAll(new ImageView(db.getImage()));
//					System.out.println(db.getImage());
//					System.out.println(myPicker.getRoot().lookup(db.getString()));
					UnitView imv = ((UnitView)(myPicker.getRoot().lookup("#" + db.getString()))).clone();
					System.out.println(imv);
					System.out.println(imv.getFitHeight());
					target.adjustUnitViewScale(imv);
					target.adjustUnitViewXY(imv, event.getSceneX(), event.getSceneY());
					target.getModel().addTerrain(imv.getX(), imv.getY(), imv.getUnit());
					System.out.println("X: " + event.getSceneX());
					System.out.println("Y: " + event.getSceneY());
					System.out.println("Grid X: " + imv.getX());
					System.out.println("Grid Y: " + imv.getY());
					System.out.println(myPicker.myEditInfo.getChildren());
					imv.addContextMenu(target, imv);
					target.addToPane(imv);
//					imv.setOnMouseClicked(new EventHandler<MouseEvent>(){
//						@Override
//						public void handle(MouseEvent event) {
//							target.getChildren().remove(imv);
//						}
//					});
//					UnitView uv = new UnitView(db.getImage());
//					target.getChildren().addAll(uv);
//					myModel.addTerrain(uv.getX(), uv.getY(), uv.getUnit());
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
	
//	public void setupNodeTarget(Node target) {
//		
//		target.setOnDragOver(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				System.out.println("Dragging over Node...");
//				if (event.getGestureSource() != target &&
//						event.getDragboard().hasString()) {
//					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//				}
//				event.consume();
//			}
//		});
//		
//		target.setOnDragEntered(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				System.out.println("Drag entered...");
//				if (event.getGestureSource() != target &&
//						event.getDragboard().hasString()) {
//				}
//				event.consume();
//			}
//		});
//		
//		target.setOnDragExited(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* mouse moved away, remove the graphical cues */
//				System.out.println("Drag exited...");
//				event.consume();
//			}
//		});
//		
//		target.setOnDragDropped(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				System.out.println("Drag dropped...");
//				Dragboard db = event.getDragboard();
//				boolean success = false;
//				if (db.hasString()) {
//					System.out.println("Name: " + db.getString());
//					success = true;
//				}
//				event.setDropCompleted(success);
//				event.consume();
//			}
//		});
//	}
	
}
