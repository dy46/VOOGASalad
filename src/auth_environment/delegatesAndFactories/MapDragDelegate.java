//package auth_environment.delegatesAndFactories;
//
//import auth_environment.Models.UnitView;
//import auth_environment.view.Tile;
//import game_engine.game_elements.Unit;
//import javafx.event.EventHandler;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.ClipboardContent;
//import javafx.scene.input.DragEvent;
//import javafx.scene.input.Dragboard;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.input.TransferMode;
//import javafx.scene.layout.Pane;
//
//public class MapDragDelegate {
//	
//	private NodeFactory myNodeFactory;
//	public MapDragDelegate() {
//		myNodeFactory = new NodeFactory();
//		// TODO Auto-generated constructor stub
//	}
//	
//	public void addUnitViewSource(UnitView source) {
//		source.setOnDragDetected(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent event) {
//				System.out.println("Drag detected..."); 
//				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//				ClipboardContent content = new ClipboardContent();
//				content.putImage(source.getImage());
//				db.setContent(content);
//				System.out.println("Name: " + db.getImage());
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
//	
//	
//	public void setupTarget(Pane target) {
//		target.getShape().setOnDragOver(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				if (event.getGestureSource() != target &&
//						event.getDragboard().hasString()) {
//					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//				}
//				event.consume();
//			}
//		});
//
//		target.getShape().setOnDragEntered(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				if (event.getGestureSource() != target &&
//						event.getDragboard().hasString()) {
////					target.showCurrentElement();
//				}
//				event.consume();
//			}
//		});
//
//		target.getShape().setOnDragExited(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				/* mouse moved away, remove the graphical cues */
//				if(!target.hasElement()){
//						target.clear();
//				}
//				event.consume();
//			}
//		});
//
//		target.getShape().setOnDragDropped(new EventHandler<DragEvent>() {
//			public void handle(DragEvent event) {
//				Dragboard db = event.getDragboard();
//				boolean success = false;
//				if (db.hasString()) {
////					target.updateElement( (GameElement) (db.getContent(DragDelegate.gameElementFormat)) );
//					System.out.println("Name: " + db.getString());
//					target.setName(db.getString());
//					target.placeCurrentElement(); 
//					success = true;
//					System.out.println("hi");
//				}
//				event.setDropCompleted(success);
//				event.consume();
//			}
//		});
//		
//		
//	}
//
//}
