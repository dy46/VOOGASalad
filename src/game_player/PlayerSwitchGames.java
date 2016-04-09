package game_player;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerSwitchGames implements IGUIObject {
	
	private static final int WINDOW_SIZE = 500;
	private ResourceBundle myResources;
	private Button myButton;
	private GridPane myRoot;
	private SwitchWindow mySwitchWindow;
	private Menu myMenu;
	private MenuBar myMenuBar;
	
	public PlayerSwitchGames(ResourceBundle r) {
		myResources = r;
		myRoot = new GridPane();
		mySwitchWindow = new SwitchWindow(r);
	}

	@Override
	public Node createNode() {
		myMenuBar = new MenuBar();
		myMenu = new Menu(myResources.getString("Options"));
		MenuItem switchGame = new MenuItem(myResources.getString("SwitchGame"));
		switchGame.setOnAction(event -> openSwitchWindow());
		MenuItem restartGame = new MenuItem(myResources.getString("RestartGame"));
//		restartGame.setOnAction(event -> doSomething());
		myMenu.getItems().addAll(switchGame, restartGame);
		myMenuBar.getMenus().add(myMenu);
		return myMenuBar;
	}
	
	private void openSwitchWindow() {
		Stage stage = new Stage();
		myRoot = mySwitchWindow.createWindow();
		Scene switchWindowScene = new Scene(myRoot, WINDOW_SIZE, WINDOW_SIZE);
		stage.setScene(switchWindowScene);
		stage.show();
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
