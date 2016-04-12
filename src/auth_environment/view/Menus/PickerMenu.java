package auth_environment.view.Menus;

import java.util.Arrays;
import java.util.List;

import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.Grid;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PickerMenu extends Accordion {

	public PickerMenu() {
		this.getPanes().addAll(defaultPanes());
		// TODO Auto-generated constructor stub
	}

	public PickerMenu(TitledPane... titledPanes) {
		super(titledPanes);
		// TODO Auto-generated constructor stub
	}
	
	private List<TitledPane> defaultPanes() {
		TitledPane myTowers = new TitledPane();
		
		
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("Health: "), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label("Coordinates: "), 0, 1);
		grid.add(new TextField(), 1, 1);
		grid.add(new Label("Reward "), 0, 2);
		grid.add(new TextField(), 1, 2); 
		myTowers.setText("Towers");
		myTowers.setContent(grid);
		
		TitledPane myEnemies = new TitledPane();
		myEnemies.setText("Enemies");
		
		TitledPane myTerrains = new TitledPane();
		myTerrains.setText("Terrains");
		
//		Text hello = new Text("Hello"); 
//		DragDelegate drag = new DragDelegate();
//		drag.setupSource(hello);
		myTerrains.setContent(hello);
		
		List<TitledPane> myPanes = Arrays.asList(myTowers,myEnemies,myTerrains);
		return myPanes; 
	}
}
