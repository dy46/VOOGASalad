package auth_environment.delegatesAndFactories;

import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.view.PathPoint;
import auth_environment.view.UnitPicker;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Created by BrianLin on 4/11/2016
 * Team member responsible: Brian
 *
 * This delegate class adds dragging functions. Woowee dongerino. 
 */

public class DragDelegate {

	public DragDelegate() {

	}

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

	public void setUpNodeTarget(IMapPane target, UnitPicker myPicker) {

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
					UnitView imv = ((UnitView)(myPicker.getRoot().lookup("#" + db.getString()))).clone();
					target.adjustUnitViewScale(imv);
					target.adjustUnitViewXY(imv, event.getSceneX(), event.getSceneY());
					imv.addContextMenu(target, imv);
					target.addToPane(imv);
					target.getModel().addTerrain(imv.getX(), imv.getY(), imv.getUnit());
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}
	
	public void setUpNodeTarget(PathPoint pathPoint, UnitPicker picker, IPathTabModel pathModel) {
		Circle target = pathPoint.getCircle(); 
		
		target.setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			e.consume();
		});
		
		target.setOnDragEntered(e -> e.consume());

		target.setOnDragExited(e -> e.consume());
		
		target.setOnDragDropped(e -> {
			e.acceptTransferModes(TransferMode.COPY);
			Dragboard db = e.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				UnitView uv = ((UnitView)(picker.getRoot().lookup("#" + db.getString())));
				pathModel.setActiveUnit(uv.getUnit());
				Position pos = pathPoint.getPosition(); 
				pathModel.addSpawnToActiveLevel(pos);
				uv.getUnit().getProperties().setMovement(new Movement(pos));
				uv.getUnit().getProperties().setPosition(pos);
				success = true;
			}
			e.setDropCompleted(success);
			e.consume();
		});
	}
}
