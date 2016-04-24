package game_player.view;

import java.util.ResourceBundle;

import game_engine.GameEngineInterface;
import game_engine.game_elements.Unit;
import game_player.GameDataSource;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameHUD {
	
	private ResourceBundle myResources;
	private ImageView unitImage;
	private Button upgradeButton;
	private Button sellButton;
	private Label unitType;
	private HBox HUDBox;
	
	public GameHUD(ResourceBundle r) {
		myResources = r;
	}
	
	public Node createNode() {
		HUDBox = new HBox();
		addToHUD();
		whenNothingSelected();
		return HUDBox;
	}
	
	public void addToHUD() {
		VBox unitStuff = new VBox();
		unitImage = new ImageView();
		unitType = new Label();
		unitStuff.getChildren().addAll(unitType, unitImage);
		upgradeButton = new Button();
		sellButton = new Button();
		HUDBox.getChildren().addAll(unitStuff, upgradeButton, sellButton);
		HUDBox.setAlignment(Pos.CENTER_LEFT);
		HUDBox.
	}
	
	public void whenTowerSelected(Unit tower) {
		System.out.println(tower.toString());
		unitImage.setImage(new Image(tower.toString() + ".png"));
		unitType.setText(tower.getName());
		upgradeButton.setText(myResources.getString("HUDUpgradeButton"));
		sellButton.setText(myResources.getString("HUDSellButton"));
	}
	
	public void whenNothingSelected() {
		unitImage.setImage(new Image("Tower.png"));
		unitType.setText("No Selection");
		upgradeButton.setText(myResources.getString("HUDUpgradeButton"));
		sellButton.setText(myResources.getString("HUDSellButton"));
	}
}
