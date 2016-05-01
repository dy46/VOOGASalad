package game_player.view;

import java.io.File;
import java.util.ResourceBundle;
import auth_environment.IAuthEnvironment;
import game_data.IDataConverter;
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

	private static final String BACKGROUND = "background";
	private static final int NUMBER_OF_PLAY_CYCLES = -1;
	private static final String DEFAULT_CSS = "PlayerTheme1.css";
    private static final String DEFAULT_PACKAGE = "game_player/view/";
    private static final String GUI_RESOURCE = "game_player/resources/GUI";
    private int windowWidth;
    private int windowHeight;
    private Scene myScene;
    private AnchorPane myRoot;
    private TabPane myTabs;
    private ResourceBundle myResources;
    private GameEngineInterface gameEngine;
    private IMainView myMainView;
    private File currentGame;
    private MediaPlayer myMusic;
    private IDataConverter<IAuthEnvironment> writer;

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

        AnchorPane.setTopAnchor(myTabs, Double.valueOf(myResources.getString("TabsOffset")));
        AnchorPane.setTopAnchor(newTabButton, Double.valueOf(myResources.getString("NewTabOffset")));
        AnchorPane.setRightAnchor(newTabButton, Double.valueOf(myResources.getString("TabsOffset")));

        myScene.getStylesheets().add(DEFAULT_PACKAGE + DEFAULT_CSS);
        myScene.getRoot().getStyleClass().add(BACKGROUND);

        myRoot.getChildren().addAll(myTabs, newTabButton);

        return myScene;
    }

    private IAuthEnvironment readData () {
        writer = new Serializer<>();
        currentGame = writer.chooseXMLFile();
        IAuthEnvironment gameData = (IAuthEnvironment) writer.loadFromFile(currentGame);
        System.out.println("SETTING BREAKPOINT");
        return gameData;
    }

    protected void createNewTab (IAuthEnvironment data) {
//        gameEngine = new TestingEngineWorkspace();
//        gameEngine.setUpEngine(null);
    	createNewEngine(data);
        Tab tab = new PlayerMainTab(gameEngine, myResources, myScene, myMainView, this,
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
    	createNewTab(writer.loadFromFile(currentGame));
    }
    
    public void loadFromXML(File file) {
    	deleteCurrentTab();
    	createNewTab(writer.loadFromFile(file));
    }
    
    public void loadNextLevel() {
    	NextLevelScreen screen = new NextLevelScreen(myResources, this);
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
