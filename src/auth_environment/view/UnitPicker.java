package auth_environment.view;

import java.util.List;

import auth_environment.Models.UnitPickerModel;
import auth_environment.Models.UnitView;
import game_engine.game_elements.Unit;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;

public class UnitPicker {

	private UnitPickerModel myUnitPickerModel;
	public UnitPicker(){
		myUnitPickerModel = new UnitPickerModel();
	}
	
	public UnitPicker(List<Unit> units){
		init(myUnitPickerModel.setUnits(units));
	}
	
	public void init(List<UnitView> units){
		TitledPane editPane = new TitledPane();
		ScrollPane editScrollPane = new ScrollPane();
		FlowPane editInfo = new FlowPane();
		
		editPane.setPrefSize(200.0, 800.0);
		editScrollPane.setContent(editInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		editScrollPane.setFitToWidth(true);
		
		editPane.setText("Edit");
		editPane.setContent(editScrollPane);
		editPane.setCollapsible(false);
		for(UnitView uv: units){
			editInfo.getChildren().addAll(uv);
		}
	}
}
