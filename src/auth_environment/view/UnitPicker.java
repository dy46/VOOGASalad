package auth_environment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.UnitView;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.tabs.ElementTab;
import auth_environment.view.tabs.UnitTab;
import game_engine.game_elements.Unit;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;

public class UnitPicker{

	private TitledPane myEditPane;
	private FlowPane myEditInfo;
	
	private List<UnitView> myUnitViews;
	
	private static final String LABEL_PACKAGE = "auth_environment/properties/unit_picker_labels";
	private static final ResourceBundle myLabelsBundle = ResourceBundle.getBundle(LABEL_PACKAGE);
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/unit_picker_dimensions";
	private static final ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	public UnitPicker(String name){
		init(name);
		this.myUnitViews = new ArrayList<UnitView>(); 
	}
	
	public UnitPicker(String name, List<Unit> units){
		init(name);
		this.myUnitViews = new ArrayList<UnitView>();
		this.setUnits(units);
	}
	
	public void setName(String name){
		init(name);
	}
	
	private void init(String name){
		ScrollPane editScrollPane = new ScrollPane();
		myEditPane = new TitledPane();
		myEditInfo = new FlowPane();
		
		myEditPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("width")),Double.parseDouble(myDimensionsBundle.getString("height")));
		editScrollPane.setContent(myEditInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		editScrollPane.setFitToWidth(true);
		
		myEditPane.setText(name);
		myEditPane.setContent(editScrollPane);
		myEditPane.setCollapsible(false);
	}
	
	public void setUnits(List<Unit> units) {
		this.myUnitViews.clear();
		this.myEditInfo.getChildren().clear();
		units.stream().forEach(s -> myUnitViews.add(new UnitView(s, s.toString() + myLabelsBundle.getString("pngLabel"))));
		this.myEditInfo.getChildren().addAll(this.myUnitViews);
		setDragable();
	}
	
	public void setDragable(){
		myUnitViews.stream().forEach(e -> {
			DragDelegate drag = new DragDelegate();
			drag.addUnitViewSource(e);
		});
	}
	
	public void add(Unit unit, ElementTab elementTab){
		UnitView uv = new UnitView(unit, unit.toString() + myLabelsBundle.getString("pngLabel"));
		myEditInfo.getChildren().add(uv);

	}

	public TitledPane getRoot(){
		return myEditPane;
	}

	public void setClickable(UnitTab elementTab) {
		myUnitViews.stream().forEach(e -> {
			e.setOnMouseClicked(l -> elementTab.updateMenu(e.getUnit()));
		});
		
	}
}
