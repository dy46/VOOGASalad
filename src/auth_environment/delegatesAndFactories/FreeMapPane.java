package auth_environment.delegatesAndFactories;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import javafx.scene.layout.Pane;

public class FreeMapPane extends Pane implements IMapPane{
	
	private int adjustFactor;
	private MapEditorTabModel myModel;
	private List<UnitView> myTerrains;
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	
	public FreeMapPane(MapEditorTabModel model) {
		this.myModel = model;
		this.adjustFactor = 10;
		myTerrains = new ArrayList<UnitView>();
	}
	
	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getHeight()/adjustFactor);
		uv.setFitHeight(this.getWidth()/adjustFactor);
	}
	
	public void adjustUnitViewXY(UnitView uv, double x, double y){
		uv.setX(x);
		uv.setY(y - uv.getFitHeight());
	}
	
	public MapEditorTabModel getModel(){
		return myModel;
	}
	
	public void addToPane(UnitView uv){
		this.myTerrains.add(uv);
		this.getChildren().add(uv);
		System.out.println("Added" + " X: " + uv.getX() + " Y: " + uv.getY());
	}
	
	public Pane getRoot(){
		return this;
	}
	
	public void refresh(){
		if(!this.myModel.getPlacedUnits().isEmpty()) {
			this.getChildren().clear();
			this.myModel.getPlacedUnits().stream().forEach(e -> {
				System.out.println(e.toString());
				UnitView temp = new UnitView (e, e.toString() + myNamesBundle.getString("defaultImageExtensions"));
				temp.addContextMenu(this, temp);
				this.addToPane(temp);
			});
		}
	}
	
}
