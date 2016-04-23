package game_player.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_player.GameDataSource;
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
    private List<ImageView> children;
    private Button sellButton;
    private List<Button> upgrades;
    private Label unitType;
    private HBox HUDBox;
    private GameEngineInterface engine;

    public GameHUD (ResourceBundle r) {
        myResources = r;
        upgrades = new ArrayList<>();

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
        VBox unitStuff = new VBox();
        unitImage = new ImageView();
        unitType = new Label();
        unitStuff.getChildren().addAll(unitImage, unitType);
        sellButton = new Button();
        HUDBox.getChildren().addAll(unitStuff, sellButton);
        this.children = new ArrayList<>();
    }

    public void whenTowerSelected (Unit tower) {
        unitImage.setImage(new Image(tower.toString() + ".png"));
        for (int i = 0; i < upgrades.size(); i++) {
            HUDBox.getChildren().remove(upgrades.get(i));
        }
        upgrades.clear();
        addUpgrades(tower);
        for (int i = 0; i < children.size(); i++) {
            HUDBox.getChildren().remove(children.get(i));
        }
        children.clear();
        for (int i = 0; i < tower.getChildren().size(); i++) {
            ImageView child =
                    new ImageView(new Image(tower.getChildren().get(i).toString() + ".png"));
            HUDBox.getChildren().add(child);
            addUpgrades(tower.getChildren().get(i));
            children.add(child);
        }
        sellButton.setText(myResources.getString("HUDSellButton"));
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

    public void whenNothingSelected () {
        unitImage.setImage(new Image("health.png"));
        unitType.setText("Testing");
        sellButton.setText(myResources.getString("HUDSellButton"));
    }
}
