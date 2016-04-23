package game_player.view;

import java.util.ResourceBundle;

import game_player.GameDataSource;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GameStore implements IGUIObject {
	
	private ResourceBundle myResources;
	private GameDataSource myData;
	private IGameView myView;
	
	private Label myMoneyLabel;
	private ListView myShop;
	private double myMoney;
	
	public GameStore(ResourceBundle r, GameDataSource data, IGameView view) {
		myResources = r;
		myData = data;
		myView = view;
	}

	@Override
	public Node createNode() {
		myMoneyLabel = new Label();
		myShop = new ListView();
		
		return null;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub
		
	}

}
