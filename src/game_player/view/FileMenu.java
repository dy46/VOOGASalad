package game_player.view;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FileMenu extends PlayerMenu{

	private static final int WINDOW_SIZE = 500;
	private Stage menuStage;
	private ResourceBundle myResources;
	private MenuMaker menuMaker;
	private Menu myMenu;
	
	public FileMenu(ResourceBundle r, IGameView view) {
		super(r, view);
		myResources = r;
		menuMaker = new MenuMaker(r);
	}
	
	public Menu createMenu() {
		myMenu = super.createNewMenu("FileMenu");
		return myMenu;
	}
}
