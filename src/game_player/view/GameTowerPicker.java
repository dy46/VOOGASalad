package game_player.view;

import java.util.List;
import java.util.ResourceBundle;

import game_engine.game_elements.Unit;
import game_engine.GameEngineInterface;
import game_player.GameDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class GameTowerPicker implements IGUIObject {
	
	private static final int LISTVIEW_WIDTH = 150;
	private ResourceBundle myResources;
	private GameDataSource myData;
	private IGameView myView;
	private GameEngineInterface myEngine;
	private ListView<Unit> myListView;
	private ObservableList<Unit> myTowers;
	
	public GameTowerPicker(ResourceBundle r, GameDataSource data, IGameView view) {
		myResources = r;
		myData = data;
		myView = view;
		myEngine = myView.getGameEngine();
		myTowers = FXCollections.observableArrayList();
	}

	@Override
	public Node createNode() {
		updateTowerList();
		VBox box = new VBox();
		myListView = new ListView<>();
		
		Callback<ListView<Unit>, ListCell<Unit>> cellFactory = (e -> {
			return new TowerCell();
		});
		
		myListView.setItems(myTowers);
		myListView.setCellFactory(cellFactory);
		myListView.setOnMouseClicked(e -> {
			myView.changeClickedTower(myListView.getSelectionModel().getSelectedItem().toString());
		});
		
		myListView.setPrefWidth(LISTVIEW_WIDTH);
		
		box.getChildren().add(myListView);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		
		return box;
	}

	@Override
	public void updateNode() {
		updateTowerList();
	}
	
	private void updateTowerList() {
		List<Unit> allTowerTypes = myEngine.getTowerTypes();
		for (int i = myTowers.size(); i < allTowerTypes.size(); i++) {
			myTowers.add(allTowerTypes.get(i));
		}
	}
	
	public double transformDirection(Unit u) {
		return -u.getProperties().getVelocity().getDirection() + 90;
	}
}
