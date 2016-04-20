//package auth_environment.view;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import auth_environment.delegatesAndFactories.DragDelegate;
//import auth_environment.view.RecTile;
//import game_engine.game_elements.Enemy;
//import game_engine.game_elements.Terrain;
//import game_engine.game_elements.Tower;
//import game_engine.game_elements.Unit;
//import javafx.scene.control.Accordion;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.FlowPane;
//
//
//public class ElementPicker extends Accordion {
//	
//	private List<Tower> myTowerList;
//	private List<Unit> myEnemyList;
//	private List<Unit> myTerrainList;
//	FlowPane myTowerPane;
//	FlowPane myEnemyPane;
//	FlowPane myTerrainPane;
//	
//	public ElementPicker() {
//		this.getPanes().addAll(defaultPanes());
//		myTowerList = new ArrayList<>();
//		myEnemyList = new ArrayList<>();
//		myTerrainList = new ArrayList<>();
//	}
//
//	public ElementPicker(TitledPane... titledPanes) {
//		super(titledPanes);
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	
//	public void updateTower(Tower t){
//		 myTowerList.add(t);
//		 RecTile tile = new RecTile(50,50);
//		 tile.updateElement(t);
//		 DragDelegate drag = new DragDelegate();
//		 drag.setupSource(tile);
//		 myTowerPane.getChildren().add(tile.getShape());
//	}
//	
//	public void updateEnemy(Enemy t){
//		 myEnemyList.add(t);
//		 RecTile tile = new RecTile(50,50);
//		 tile.updateElement(t);
//		 DragDelegate drag = new DragDelegate();
//		 drag.setupSource(tile);
//		 myEnemyPane.getChildren().add(tile.getShape());
//	}
//	
//	public void updateTerrain(Terrain t){
//		 myTerrainList.add(t);
//		 RecTile tile = new RecTile(50,50);
//		 tile.updateElement(t);
//		 DragDelegate drag = new DragDelegate();
//		 drag.setupSource(tile);
//		 myTerrainPane.getChildren().add(tile.getShape());
//	}
//	
//	
//	
//	private List<TitledPane> defaultPanes() {
//		myTowerPane = new FlowPane();
//		myEnemyPane = new FlowPane();
//	    myTerrainPane = new FlowPane();
//		TitledPane myTowers = new TitledPane();
//		   int w = 50;
//		   int h = 50;
//	 	 
//		myTowerPane.setVgap(4);
//		myTowerPane.setHgap(4);
////		GridPane grid = new GridPane();
////		grid.setVgap(4);
////		grid.setPadding(new Insets(5, 5, 5, 5));
////		grid.add(new Label("Health: "), 0, 0);
////		grid.add(new TextField(), 1, 0);
////		grid.add(new Label("Coordinates: "), 0, 1);
////		grid.add(new TextField(), 1, 1);
////		grid.add(new Label("Reward "), 0, 2);
////		grid.add(new TextField(), 1, 2); 
//		myTowers.setText("Towers");
////		myTowers.setContent(grid);
//	 	myTowers.setContent(myTowerPane);
//		
//		TitledPane myEnemies = new TitledPane();
//		myEnemies.setText("Enemies");
//		myEnemies.setContent(myEnemyPane);
//		myEnemyPane.setVgap(4);
//		myEnemyPane.setHgap(4);
//		
//		TitledPane myTerrains = new TitledPane();
//		myTerrains.setText("Terrains");
//		myTerrains.setContent(myTerrainPane);
//		myTerrainPane.setVgap(4);
//		myTerrainPane.setHgap(4);
//		
////		RecTile tile = new RecTile(20, 20);
////		
////		
//////		Text hello = new Text("Hello"); 
////		DragDelegate drag = new DragDelegate();
////		drag.setupSource(tile);
////		myTerrains.setContent(tile.getShape());
//		
//		List<TitledPane> myPanes = Arrays.asList(myTowers,myEnemies,myTerrains);
//		return myPanes; 
//	}
//
//	public List<Tower> getTowers() {
//		return myTowerList;
//	}
//	
//	public List<Unit> getEnemies() {
//		return myEnemyList;
//	}
//	
//	public List<Unit> getTerrains() {
//		return myTerrainList;
//	}
//	
//	
//}