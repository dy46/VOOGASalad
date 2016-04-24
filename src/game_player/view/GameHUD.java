package game_player.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameHUD {

    private ResourceBundle myResources;
    private ImageView unitImage;
    private List<ImageView> children;
    private Button sellButton;
    private List<Button> upgrades;
    private Label unitType;
    private HBox HUDBox;
    private GameEngineInterface engine;
    private IGameView gameView;

    public GameHUD (ResourceBundle r) {
        myResources = r;
        upgrades = new ArrayList<>();
        children = new ArrayList<>();

    }
    
    public void setGameView(IGameView gameView) {
        this.gameView = gameView;
    }

    public void setEngine (GameEngineInterface engine) {
        this.engine = engine;
    }

    public Node createNode () {
        HUDBox = new HBox();
        addToHUD();
        whenNothingSelected();
        return HUDBox;
    }

    public void addToHUD () {
    	HUDBox.getChildren().clear();
        VBox unitStuff = new VBox();
        unitStuff.setAlignment(Pos.CENTER);
        unitImage = new ImageView();
        unitType = new Label();
        unitStuff.getChildren().addAll(unitType, unitImage);
        sellButton = new Button();
        HUDBox.getChildren().addAll(unitStuff, sellButton);
        HUDBox.setAlignment(Pos.CENTER_LEFT);
    }

    public void whenTowerSelected (Unit tower) {
    	addToHUD();
        gameView.setSpecificUnitIsClicked(tower);
        unitType.setText(tower.getName());
        unitImage.setImage(new Image(tower.toString() + ".png"));
        removeUpgrades(upgrades);
        addUpgrades(tower);
        removeUpgrades(children);
        addChildrenUpgrades(tower);
        sellButton.setText(myResources.getString("HUDSellButton"));
        sellButton.setOnMouseClicked(e -> engine.sellUnit(tower));
    }
    
    public <E> void removeUpgrades(List<E> upgradesList) {
    	for (int i = 0; i < upgradesList.size(); i++) {
            HUDBox.getChildren().remove(upgradesList.get(i));
        }
    	upgradesList.clear();
    }

    public void addUpgrades (Unit u) {
        for (int i = 0; i < engine.getUpgrades(u).size(); i++) {
            Button upgradeButton = new Button();
            Affector affector = engine.getUpgrades(u).get(i);
            upgradeButton.setOnMouseClicked(e -> engine.applyUpgrade(u, affector));
            upgradeButton.setText(affector.getClass().getSimpleName());
            upgrades.add(upgradeButton);
            HUDBox.getChildren().add(upgradeButton);
        }
    }
    
    public void addChildrenUpgrades(Unit tower) {
        for (int i = 0; i < tower.getChildren().size(); i++) {
            ImageView child =
                    new ImageView(new Image(tower.getChildren().get(i).toString() + ".png"));
            HUDBox.getChildren().add(child);
            addUpgrades(tower.getChildren().get(i));
            children.add(child);
        }
    }

    public void whenNothingSelected () {
    	HUDBox.getChildren().clear();
    	Rectangle rect = new Rectangle(500, 100);
    	rect.setFill(Color.DARKGRAY);
    	HUDBox.getChildren().add(rect);
        if(gameView != null) {
            gameView.setSpecificUnitIsClicked(null);
        }
//        unitImage.setImage(new Image("Tower.png"));
//        unitType.setText("No Selection");
//        sellButton.setText(myResources.getString("HUDSellButton"));
    }
}
