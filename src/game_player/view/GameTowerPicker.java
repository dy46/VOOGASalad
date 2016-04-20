package game_player.view;

import java.util.List;
import java.util.ResourceBundle;

import game_engine.game_elements.Unit;
import game_engine.games.GameEngineInterface;
import game_player.GameDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameTowerPicker implements IGUIObject {
	
	private ResourceBundle myResources;
	private GameDataSource myData;
	private IGameView myView;
	private GameEngineInterface myEngine;
	private List<ImageView> towerTypes;
	private ListView<TowerCell> myListView;
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
		myListView = new ListView<>();
		return null;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}
	
	private void updateTowerList() {
		List<Unit> allTowerTypes = myEngine.getTowerTypes();
		for (int i = towerTypes.size(); i < allTowerTypes.size(); i++) {
			String name = allTowerTypes.get(i).toString();
			Image img = new Image(name + ".png");
			ImageView imgView = new ImageView(img);
			imgView.setRotate(transformDirection(allTowerTypes.get(i)));
			towerTypes.add(imgView);
		}
	}
	
	public double transformDirection(Unit u) {
		return -u.getProperties().getVelocity().getDirection() + 90;
	}
}
