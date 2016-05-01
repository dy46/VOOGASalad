package auth_environment.delegatesAndFactories;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.MapEditorTabModel;
import auth_environment.Models.UnitView;
import auth_environment.Models.Interfaces.IMapPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridMapPane extends GridPane implements IMapPane {
	
	private int numCols;
	private int numRows;
	private MapEditorTabModel myModel;
	private List<UnitView> myTerrains;
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public GridMapPane(MapEditorTabModel model, int cols, int rows) {
		this.myModel = model;
		this.setNumColsRows(cols, rows);
		this.setGridLinesVisible(true);
		myTerrains = new ArrayList<UnitView>();
	}
	

	public void adjustUnitViewScale(UnitView uv){
		uv.setFitWidth(this.getHeight()/numCols-3);
		uv.setFitHeight(this.getWidth()/numRows-3);
		System.out.println(uv.getFitHeight());
	}
	
	public void adjustUnitViewXY(UnitView uv, double x, double y){
		uv.setX(x);
		uv.setY(y - uv.getFitHeight());
	}
	
	public void setNumColsRows(int cols, int rows){
		this.numCols = cols;
		this.numRows = rows;
	    for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            this.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);         
        }
	}
	
	public MapEditorTabModel getModel(){
		return myModel;
	}
	
	public void addToPane(UnitView uv){
		double originalX= uv.getX();
		double originalY = uv.getY();
		convertToGridPosition(uv);
		this.add(uv, convertToColumn(originalX), convertToRow(originalY));
		myTerrains.add(uv);
	}
	
	public void convertToGridPosition(UnitView uv){
		uv.setX(uv.getFitWidth()*(convertToColumn(uv.getX())));
		uv.setY(uv.getFitHeight()*(convertToRow(uv.getY())));
	}
	
	private int convertToColumn(double x){
		return ((int)(x/(this.getWidth()/numCols)));
	}
	
	private int convertToRow(double y){
		return ((int)(y/(this.getHeight()/numRows)));
	}

	public GridPane getRoot(){
		return this;
	}
	
	public void setModel(MapEditorTabModel model){
		this.myModel = model;
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
