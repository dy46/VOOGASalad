package auth_environment.view;

import java.util.ArrayList;
import java.util.List;

import auth_environment.Models.UnitView;
import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.tabs.ElementTab;
import game_engine.game_elements.Unit;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;

public class UnitPicker{

	private TitledPane myEditPane;
	public FlowPane myEditInfo;
	
	private List<UnitView> myUnitViews;
	
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
		
		myEditPane.setPrefSize(200.0, 800.0);
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
		//units.stream().forEach(s -> myUnitViews.add(new UnitView(s, "unicornCat.gif")));
		units.stream().forEach(s -> myUnitViews.add(new UnitView(s, s.toString() + ".png")));
		this.myEditInfo.getChildren().addAll(this.myUnitViews);
		setDragable();
	}
	
	public void setDragable(){
//		myUnitViews.stream().forEach(s->addUnitViewSource(s));
		myUnitViews.stream().forEach(e -> {
			DragDelegate drag = new DragDelegate();
			drag.addUnitViewSource(e);
		});
	}
	
	public void add(Unit unit, ElementTab elementTab){
		UnitView uv = new UnitView(unit, unit.toString() + ".png");
		myEditInfo.getChildren().add(uv);

	}

	public TitledPane getRoot(){
		return myEditPane;
	}

	public void setClickable(ElementTab elementTab) {
		myUnitViews.stream().forEach(e -> {
			e.setOnMouseClicked(l -> elementTab.updateMenu(e.getUnit()));
		});
		
	}
}
