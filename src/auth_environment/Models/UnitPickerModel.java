package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.Models.Interfaces.IUnitPickerModel;
import auth_environment.delegatesAndFactories.DragDelegate;
import game_engine.game_elements.Unit;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class UnitPickerModel implements IUnitPickerModel {
	
	private List<UnitView> myUnitViews;

	public UnitPickerModel(){
		myUnitViews = new ArrayList<UnitView>();
	}
	
	@Override
	public List<UnitView> setUnits(List<Unit> units) { 
		// TODO: check that s.getImgName() works
		units.stream().forEach(s -> myUnitViews.add(new UnitView(s, "unicornCat.gif")));
		setDragable();
		System.out.println("Successfully set units!");
		return myUnitViews;
	}
	
	
	public void setDragable(){
//		myUnitViews.stream().forEach(s->addUnitViewSource(s));
		addUnitViewSource(myUnitViews.get(0));
	}
	
	@Override
	public UnitView getUnitViewWithHash(int hash) {
		for (UnitView uv : this.myUnitViews) {
			if (uv.hashCode() == hash) {
				return uv;
			}
		}
		return null; // TODO: throw some error
	}
	
// To be refactored out
	public void addUnitViewSource(UnitView source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Drag detected..."); 
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putImage(source.getImage());
				db.setContent(content);
				System.out.println("Name: " + db.getImage());
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

}
