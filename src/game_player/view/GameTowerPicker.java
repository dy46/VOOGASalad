package game_player.view;

import java.util.List;
import java.util.ResourceBundle;
import game_engine.game_elements.Unit;
import game_engine.GameEngineInterface;
import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    public GameTowerPicker (ResourceBundle r, GameDataSource data, IGameView view) {
        myResources = r;
        myData = data;
        myView = view;
        myEngine = myView.getGameEngine();
        myTowers = FXCollections.observableArrayList();
    }

    @Override
    public Node createNode () {
        updateTowerList();
        VBox box = new VBox();
        myListView = new ListView<>();
        Callback<ListView<Unit>, ListCell<Unit>> cellFactory = (e -> {
            return new TowerCell();
        });
        myListView.setItems(myTowers);
        myListView.setCellFactory(cellFactory);
        myListView.setOnMouseClicked(e -> {
            myView.setUnitToPlace(myListView.getSelectionModel().getSelectedItem().toString());
        });
        myListView.setPrefWidth(LISTVIEW_WIDTH);
        box.getChildren().add(myListView);
        VBox.setVgrow(myListView, Priority.ALWAYS);
        return box;
    }

    @Override
    public void updateNode () {
        updateTowerList();
    }

    private void updateTowerList () {
        List<Unit> allTowerTypes = myEngine.getUnitController().getTowerTypes();
        for (int i = myTowers.size(); i < allTowerTypes.size(); i++) {
            if (!hasUnitImageView(allTowerTypes.get(i), myTowers)) {
                myTowers.add(allTowerTypes.get(i));
            }
        }
        for (int i = 0; i < myTowers.size(); i++) {
            if (!allTowerTypes.contains(myTowers.get(i))) {
                myTowers.remove(i);
            }
        }
    }

    public double transformDirection (Unit u) {
        return -u.getProperties().getVelocity().getDirection() + 90;
    }

    public boolean hasUnitImageView (Unit u, List<Unit> imageViews) {
        for (int i = 0; i < imageViews.size(); i++) {
            if (imageViews.get(i) == u) {
                return true;
            }
        }
        return false;
    }
}
