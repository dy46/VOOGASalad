package game_player.view;

import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import game_player.interfaces.IGameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;


public class GameHUD {
	
	private static final int IMAGEVIEW_HEIGHT = 30;

	private static final String PNG_EXTENSION = ".png";

	private static final int PANEL_SPACING = 5;

    private ResourceBundle myResources;
    private ImageView unitImage;
    private Button sellButton;
    private Label unitType;
    private HBox HUDBox;
    private GameEngineInterface engine;
    private IGameView gameView;
    private TabPane myUpgradesTab;

    public GameHUD (ResourceBundle r) {
        myResources = r;
        myUpgradesTab = new TabPane();
    }

    public void setGameView (IGameView gameView) {
        this.gameView = gameView;
    }

    public void setEngine (GameEngineInterface engine) {
        this.engine = engine;
    }

    public Node createNode () {
        HUDBox = new HBox(PANEL_SPACING);
        addToHUD();
        whenNothingSelected();
        return HUDBox;
    }

    public void addToHUD () {
    	HUDBox.getChildren().clear();
        VBox unitStuff = new VBox(PANEL_SPACING);
        unitStuff.setAlignment(Pos.CENTER);
        unitImage = new ImageView();
        unitType = new Label();
        sellButton = new Button();
        unitStuff.getChildren().addAll(new Label(myResources.getString("SelectedUnit")), unitType, unitImage, sellButton);
        HUDBox.getChildren().addAll(unitStuff, myUpgradesTab);
        HUDBox.setAlignment(Pos.CENTER_LEFT);
    }

    public void whenTowerSelected (Unit tower) {
    	addToHUD();
        gameView.setSpecificUnitIsSelected(tower);
        unitType.setText(tower.getName());
        unitImage.setImage(new Image(tower.toString() + PNG_EXTENSION));
        sellButton.setText(myResources.getString("HUDSellButton"));
        sellButton.setOnMouseClicked(e -> engine.getUnitController().sellUnit(tower));
        removeUpgrades();
        addUpgrades(tower);
        addChildrenUpgrades(tower);
    }
    
    public <E> void removeUpgrades() {
    	myUpgradesTab.getTabs().clear();
    }

    public void addUpgrades (Unit u) {
    	ListView<Affector> upgradesList = new ListView<>();
    	upgradesList.setPrefHeight(100);
    	ObservableList<Affector> theUpgrades = FXCollections.observableArrayList();
    	Tab upgradeTab = new Tab();
    	upgradeTab.setGraphic(createImageView(u));
    	Callback<ListView<Affector>, ListCell<Affector>> cellFactory = (e -> {
            return new UpgradeCell();
        });
        for (int i = 0; i < engine.getUnitController().getUpgrades(u).size(); i++) {
        	Affector affector = engine.getUnitController().getUpgrades(u).get(i);
        	theUpgrades.add(affector);
        }
        upgradesList.setItems(theUpgrades);
        upgradesList.setCellFactory(cellFactory);
        upgradesList.setOnMouseClicked(e -> {
            engine.getUnitController().applyUpgrade(u, upgradesList.getSelectionModel().getSelectedItem());
        });
        upgradeTab.setContent(upgradesList);
        myUpgradesTab.getTabs().add(upgradeTab);
    }
    
    public void addChildrenUpgrades(Unit tower) {
        for (int i = 0; i < tower.getChildren().size(); i++) {
            addUpgrades(tower.getChildren().get(i));
        }
    }

    public void whenNothingSelected () {
    	HUDBox.getChildren().clear();
    	Rectangle rect = new Rectangle(500, 150);
    	rect.setFill(Color.DARKGRAY);
    	HUDBox.getChildren().add(rect);
        if (gameView != null) {
            gameView.setSpecificUnitIsSelected(null);
        }
    }
    
    private ImageView createImageView (Unit unit) {
        String name = unit.toString();
        Image image = new Image(name + ".png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(IMAGEVIEW_HEIGHT);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}