package game_player.view;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FileMenu implements IMenuInterface{

	private static final int WINDOW_SIZE = 500;
	private Stage menuStage;
	private ResourceBundle myResources;
	private MenuMaker menuMaker;
	private Menu myMenu;
	private GridPane myRoot;
	private SwitchWindow mySwitchWindow;
	private IGameView myView;
	
	public FileMenu(ResourceBundle r, IGameView view) {
		myResources = r;
		menuMaker = new MenuMaker(r);
		mySwitchWindow = new SwitchWindow(r);
		myView = view;
	}
	
	public Menu createMenu() {
		myMenu = menuMaker.addMenu(myResources.getString("OptionsMenu"));
		String[] menuItems = myResources.getString("Options").split("/");
		for (String pair: menuItems) {
			String[] combo = pair.split(",");
			try {
                menuMaker.addMenuItem(combo[0], e -> {
                    try {
                        getClass().getDeclaredMethod(combo[1]).invoke(this);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } , myMenu);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
		}
		return myMenu;
	}
}
