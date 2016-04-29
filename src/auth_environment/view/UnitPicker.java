package auth_environment.view;

import java.util.List;

import auth_environment.Models.UnitPickerModel;
import auth_environment.Models.UnitView;
import auth_environment.view.tabs.ElementTab;
import game_engine.game_elements.Unit;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;

public class UnitPicker{

	private UnitPickerModel myUnitPickerModel;
	private TitledPane myEditPane;
	private FlowPane myEditInfo;
	
	
	public UnitPicker(String name){
		init(name);
	}
	
	public UnitPicker(String name, List<Unit> units){
		init(name);
		addUnits(units);
	}
	
	public void setName(String name){
		init(name);
	}
	
	private void init(String name){
		ScrollPane editScrollPane = new ScrollPane();
		myEditPane = new TitledPane();
		myEditInfo = new FlowPane();
		
		myEditPane.setPrefSize(200.0, 800.0);
		editScrollPane.setContent(myEditInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		editScrollPane.setFitToWidth(true);
		
		myEditPane.setText(name);
		myEditPane.setContent(editScrollPane);
		myEditPane.setCollapsible(false);
	}
	
	public void addUnits(List<Unit> units, ElementTab elementTab){
		myUnitPickerModel = new UnitPickerModel();
		for(UnitView uv: myUnitPickerModel.setUnits(units)){
			myEditInfo.getChildren().add(uv);
			uv.setOnMouseClicked(e -> elementTab.updateMenu(uv.getUnit()));
		}
	}
	
	private void addUnits(List<Unit> units){
		myUnitPickerModel = new UnitPickerModel();
		//for(UnitView uv: myUnitPickerModel.setUnits(units)){
			myEditInfo.getChildren().addAll(myUnitPickerModel.setUnits(units));
		//}
	}
	
//	public void add(Unit unit, ElementTab elementTab){
//		//UnitView uv = new UnitView(unit,"unicornCat.gif");
//		UnitView uv = new UnitView(unit, unit.getImgName());
//		myEditInfo.getChildren().add(uv);
//
//	}
//	
	
	
	
	public TitledPane getRoot(){
		return myEditPane;
	}
}
