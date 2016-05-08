// This entire file is part of my masterpiece
// Alexander Tseng

/* DragDelegate was originally created only for MapEditorPane. However, it was later used by UnitPicker. 
 * Since the DragDelegate is a utility class, the code should be general enough to allow multiple classes
 * to access. There was only one type of source that was needed to set up - the UnitView. Yet, the target 
 * varies and the interactions differs from target to target. Therfore, target specific method was needed
 * to create within the DragDelegate. However, The main issue is that although these target "differs", 
 * there are still enough similarites to create duplication in code. In order to keep the code clean, it 
 * was challenging to organize the methods such that there is no duplication within the code while 
 * successfully implmenting target specific interactions.
 * Originally, there were methods within the DragDelegate class that would directly manipulate the 
 * target. After further consideration, it occurred to me that it doesn't make sense if the DragDelegate 
 * class manipulates the target directly since the only responsibility that the DragDelegate class 
 * should have is to implement the dragging/dropping function and call methods from the target instead 
 * of directly manipulating it. Two methods were initially created for manipulating the FreePane and 
 * the GridPane.
 * However, I decided to use an interface to group the common methods from FreePane and GridPane. Then,
 * in the DragDelegate class, I would only need to pass in the interface and thus successfully reduce two 
 * methods down into one method. The result can be shown in the setUpNodeTarget method under the 
 * setOnDragDropped. It can be seen that the the code is very organized such that the DragDelegate itself 
 * does not contain methods that modify the target. Rather, it is calling the methods from the target 
 * itself. Within the method, the execution steps are clear (very readable).
 */

package auth_environment.delegatesAndFactories;

import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.view.PathPoint;
import auth_environment.view.UnitPicker;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Created by BrianLin on 4/11/2016
 * Team member responsible: Brian
 * @author Xander (Refactoring and implmentation), Brian
 * This delegate class adds dragging functions. Woowee dongerino. 
 */

public class DragDelegate {

	public void addUnitViewSource(UnitView source) {
		
		source.setOnDragDetected(event -> {
			Dragboard db = source.startDragAndDrop(TransferMode.COPY);
			ClipboardContent content = new ClipboardContent();
			source.setId(this.getClass().getSimpleName() + System.currentTimeMillis());
			content.putImage(source.getImage());
			content.putString(source.getId());
			db.setContent(content);
			event.consume();
		});
		
		source.setOnDragDone(event -> event.consume());

	}

	public void setUpNodeTarget(IMapPane target, UnitPicker myPicker) {

		Pane targetPane = target.getRoot();
		
		targetPane.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			event.consume();
		});
		
		targetPane.setOnDragEntered(event -> event.consume());
		
		targetPane.setOnDragExited(event -> event.consume());
		
		targetPane.setOnDragDropped(event -> {
			event.acceptTransferModes(TransferMode.COPY);
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				UnitView imv = ((UnitView)(myPicker.getRoot().lookup("#" + db.getString()))).clone();
				target.adjustUnitViewScale(imv);
				target.adjustUnitViewXY(imv, event.getSceneX(), event.getSceneY());
				target.addToPane(imv);
				target.getModel().addTerrain(imv.getX(), imv.getY(), imv.getUnit());
				success = true;
			}
			event.setDropCompleted(success);
			event.consume();
		});

	}
	
	public void setUpNodeTarget(PathPoint pathPoint, UnitPicker picker, UnitPicker picker0, IPathTabModel pathModel) {
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
				UnitView uv = new UnitView(); 
				uv = ((UnitView)(picker.getRoot().lookup("#" + db.getString())));
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
