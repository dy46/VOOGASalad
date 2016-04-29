package game_player.view;

import java.util.ResourceBundle;
import auth_environment.IAuthEnvironment;
import game_data.AuthSerializer;
import game_engine.EngineWorkspace;
import game_engine.GameEngineInterface;
import game_engine.TestingEngineWorkspace;
import javafx.scene.ImageCursor;
import game_engine.TestingGameData;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import main.IMainView;


public class PlayerGUI {

    private static final double TABS_OFFSET = 0;
    private static final double NEWTAB_OFFSET = 33;
    private static final String GUI_RESOURCE = "game_player/resources/GUI";
    private int windowWidth;
    private int windowHeight;
    private Scene myScene;
    private AnchorPane myRoot;
    private TabPane myTabs;
    private ResourceBundle myResources;
    private GameEngineInterface gameEngine;
    private IMainView myMainView;

    public PlayerGUI (int windowWidth, int windowHeight, IMainView main) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.myMainView = main;
        this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
    }

    public Scene createPlayerScene () {
        myRoot = new AnchorPane();
        myTabs = new TabPane();

        myScene = new Scene(myRoot, windowWidth, windowHeight);
        
        myScene.setCursor(new ImageCursor(new Image("mousecursor.png")));

        // TODO: Create resource file for UI text.
        Button newTabButton = new Button(myResources.getString("NewTabText"));
        newTabButton.setOnAction(event -> createNewTab());

        createNewTab();

        AnchorPane.setTopAnchor(myTabs, TABS_OFFSET);
        AnchorPane.setTopAnchor(newTabButton, NEWTAB_OFFSET);
        AnchorPane.setRightAnchor(newTabButton, TABS_OFFSET);

        myRoot.getChildren().addAll(myTabs, newTabButton);

        return myScene;
    }

    private IAuthEnvironment readData () {
        AuthSerializer writer = new AuthSerializer();
        IAuthEnvironment gameData = (IAuthEnvironment) writer.loadElement();
        return gameData;
    }

    private void createNewTab () {
//        gameEngine = new TestingEngineWorkspace();
//        gameEngine.setUpEngine(null);
        gameEngine = new EngineWorkspace();
        TestingGameData testData = new TestingGameData();
        gameEngine.setUpEngine(testData);
        Tab tab = new PlayerMainTab(gameEngine, myResources, myScene, myMainView,
                                    myResources.getString("TabName") + (myTabs.getTabs().size() + 1))
                                                                              .getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
    }

}
