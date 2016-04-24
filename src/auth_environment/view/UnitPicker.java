package auth_environment.view;

import java.util.List;

import auth_environment.Models.UnitPickerModel;
import auth_environment.Models.UnitView;
import game_engine.game_elements.Unit;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;

public class UnitPicker{

	private UnitPickerModel myUnitPickerModel;
	private TitledPane myEditPane;
	
	public UnitPicker(){
		myEditPane = new TitledPane();
	}
	
	public UnitPicker(List<Unit> units){
		myUnitPickerModel = new UnitPickerModel();
		myEditPane = new TitledPane();
		init(myUnitPickerModel.setUnits(units));
	}
	
	public void init(List<UnitView> units){
		ScrollPane editScrollPane = new ScrollPane();
		FlowPane editInfo = new FlowPane();
		
		myEditPane.setPrefSize(200.0, 800.0);
		editScrollPane.setContent(editInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		editScrollPane.setFitToWidth(true);
		
		myEditPane.setText("Edit");
		myEditPane.setContent(editScrollPane);
		myEditPane.setCollapsible(false);
		for(UnitView uv: units){
			editInfo.getChildren().addAll(uv);
		}
		
		
	}
	
	public void setTitle(String title){
		myEditPane.setText(title);
	}
	
	public TitledPane getRoot(){
		return myEditPane;
	}
}
