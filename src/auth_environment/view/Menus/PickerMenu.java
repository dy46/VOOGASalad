package auth_environment.view.Menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auth_environment.delegatesAndFactories.DragDelegate;
import auth_environment.view.Grid;
import auth_environment.view.RecTile;
import game_engine.game_elements.Tower;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PickerMenu extends Accordion {
	
	private List<Tower> myTowerList = new ArrayList<Tower>();
	FlowPane myPane = new FlowPane();
	
	public PickerMenu() {
		this.getPanes().addAll(defaultPanes());
		// TODO Auto-generated constructor stub
	}

	public PickerMenu(TitledPane... titledPanes) {
		super(titledPanes);
		// TODO Auto-generated constructor stub
	}
	
	public void updateMenu(List<Tower> myTowers, Tower t){
		myTowerList = myTowers;
		 RecTile tile = new RecTile(50,50);
		 tile.updateElement(t);
		 DragDelegate drag = new DragDelegate();
		 drag.setupSource(tile);
		 myPane.getChildren().add(tile.getShape());
	}
	
	private List<TitledPane> defaultPanes() {
		TitledPane myTowers = new TitledPane();
		   int w = 50;
		   int h = 50;
	 	 
		myPane.setVgap(4);
		myPane.setHgap(4);
//		GridPane grid = new GridPane();
//		grid.setVgap(4);
//		grid.setPadding(new Insets(5, 5, 5, 5));
//		grid.add(new Label("Health: "), 0, 0);
//		grid.add(new TextField(), 1, 0);
//		grid.add(new Label("Coordinates: "), 0, 1);
//		grid.add(new TextField(), 1, 1);
//		grid.add(new Label("Reward "), 0, 2);
//		grid.add(new TextField(), 1, 2); 
		myTowers.setText("Towers");
//		myTowers.setContent(grid);
	 	myTowers.setContent(myPane);
		
		TitledPane myEnemies = new TitledPane();
		myEnemies.setText("Enemies");
		
		TitledPane myTerrains = new TitledPane();
		myTerrains.setText("Terrains");
		
		RecTile tile = new RecTile(20, 20);
		
		
//		Text hello = new Text("Hello"); 
		DragDelegate drag = new DragDelegate();
		drag.setupSource(tile);
		myTerrains.setContent(tile.getShape());
		
		List<TitledPane> myPanes = Arrays.asList(myTowers,myEnemies,myTerrains);
		return myPanes; 
	}
	
	public List<Tower> exportTowers() {
		return this.myTowerList;
	}
}
