package game_player.view;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu implements IGUIObject {

    private ResourceBundle myResources;
    private Button myButton;
    private Menu myMenu;
    private MenuBar myMenuBar;
    private IGameView myView;

    public GameMenu (ResourceBundle r, GameDataSource gameData, IGameView view) {
        myResources = r;
        myView = view;
    }

    @Override
    public Node createNode () {
        myMenuBar = new MenuBar();
        myMenu = new OptionsMenu(myResources, myView).createMenu();

        myMenuBar.getMenus().add(myMenu);
        return myMenuBar;
    }

    @Override
    public void updateNode () {
        // TODO Auto-generated method stub

    }

}
