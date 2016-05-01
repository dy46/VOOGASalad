package game_player.view;

import java.io.File;
import java.util.ResourceBundle;
import auth_environment.IAuthEnvironment;
import game_data.Serializer;
import game_engine.EngineWorkspace;
import game_engine.GameEngineInterface;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.IMainView;


public class PlayerGUI {

	private static final int NUMBER_OF_PLAY_CYCLES = -1;
	private static final String DEFAULT_CSS = "PlayerTheme1.css";
    private static final String DEFAULT_PACKAGE = "game_player/view/";
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
    private IAuthEnvironment currentGame;
    private MediaPlayer myMusic;

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

        Button newTabButton = new Button(myResources.getString("NewTabText"));
        newTabButton.setOnAction(event -> createNewTab(readData()));

        createNewTab(readData());
        setMusic(myResources.getString("Audio"));

        AnchorPane.setTopAnchor(myTabs, TABS_OFFSET);
        AnchorPane.setTopAnchor(newTabButton, NEWTAB_OFFSET);
        AnchorPane.setRightAnchor(newTabButton, TABS_OFFSET);

        myScene.getStylesheets().add(DEFAULT_PACKAGE + DEFAULT_CSS);
        myScene.getRoot().getStyleClass().add("background");

        myRoot.getChildren().addAll(myTabs, newTabButton);

        return myScene;
    }

    private IAuthEnvironment readData () {
        Serializer writer = new Serializer();
        IAuthEnvironment gameData = (IAuthEnvironment) writer.loadElement();
        currentGame = gameData;
        System.out.println("SETTING BREAKPOINT");
        return gameData;
    }

    protected void createNewTab (IAuthEnvironment data) {
//        gameEngine = new TestingEngineWorkspace();
//        gameEngine.setUpEngine(null);
    	createNewEngine(data);
        Tab tab = new PlayerMainTab(gameEngine, myResources, myScene, myMainView, currentGame, this,
                                    myResources.getString("TabName") +
                                                                      (myTabs.getTabs().size() + 1))
                                                                              .getTab();
        myTabs.getTabs().add(tab);
        myTabs.getSelectionModel().select(tab);
    }
    
    private void createNewEngine(IAuthEnvironment data) {
        gameEngine = new EngineWorkspace();
//        TestingGameData testData = new TestingGameData();
        gameEngine.setUpEngine(data);
    }
    
    public void deleteCurrentTab() {
    	Tab tab = myTabs.getSelectionModel().getSelectedItem();
    	myTabs.getTabs().remove(tab);
    }
    
    public void loadNewTab() {
    	deleteCurrentTab();
    	createNewTab(readData());
    }
    
    public void restartGame() {
    	deleteCurrentTab();
    	createNewTab(currentGame);
    }
    
    public void setMusic(String name) {
		myMusic = new MediaPlayer(new Media(new File(name).toURI().toString()));
		myMusic.setCycleCount(NUMBER_OF_PLAY_CYCLES);
		myMusic.setAutoPlay(true);
    }
    
    public MediaPlayer getMusic() {
    	return myMusic;
    }
}
