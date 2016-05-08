// This entire file is part of my masterpiece.
// David Yang

/**
 * I chose this class to be part of my masterpiece because it is the main class 
 * of the front end of the game player. This is the part of the project I worked
 * the most on, and I believe the design of this section is quite good. In this
 * class, I demonstrate the use of reflection in combination with properties files
 * to add individual GUI elements easily. 
 */

package game_player.view;

import game_player.GameDataSource;
import game_player.IGameDataSource;
import game_player.interfaces.IGUIObject;
import game_player.interfaces.IGameView;
import game_player.interfaces.IPlayerTab;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.GameEngineInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.IMainView;

public class PlayerMainTab implements IPlayerTab {
	
    private static final String PACKAGE_NAME = "game_player.";
    private static final String GUI_ELEMENTS = "game_player/resources/GUIElements";
    private static final String PREFERENCES_ELEMENTS = "game_player/resources/PreferencesElements";
    private static final String PREFERENCES = "game_player/resources/Preferences";
    
    private Tab myTab;
    private BorderPane myRoot;
    private ResourceBundle myGUIResources;
    private ResourceBundle myPreferencesResources;
    private ResourceBundle elementsResources;
    private ResourceBundle preferencesResources;
    private IGameDataSource gameData;
    private List<IGUIObject> gameElements;
    private IGameView gameView;
    private GameCanvas myCanvas;
    private GameHUD myHUD;
    private Scene myScene;
    private IMainView myMainView;
    private String tabName;
    private PlayerGUI myGUI;
    private VBox gameSection;
    private VBox configurationPanel;
    private VBox gameMenu;
    private VBox gamePanel;
    private VBox towerPanel;

    private GameEngineInterface gameEngine;

    public PlayerMainTab (GameEngineInterface engine,
                          ResourceBundle r,
                          Scene scene,
                          IMainView main,
                          PlayerGUI GUI,
                          IGameDataSource data,
                          String tabName) {
        this.gameEngine = engine;
        this.gameElements = new ArrayList<>();
        this.myGUIResources = r;
        this.myPreferencesResources = ResourceBundle.getBundle(PREFERENCES);
        this.elementsResources = ResourceBundle.getBundle(GUI_ELEMENTS);
        this.preferencesResources = ResourceBundle.getBundle(PREFERENCES_ELEMENTS);
        this.myScene = scene;
        this.myMainView = main;
        this.myGUI = GUI;
        this.gameData = data;
        this.tabName = tabName;
    }

    @Override
    public Tab getTab () {
        myTab = new Tab();
        myRoot = new BorderPane();
        createUISections();
        initializePlayer();
        initializeElements(elementsResources, myGUIResources);
        initializeElements(preferencesResources, myPreferencesResources);
        placeUISections();
        myTab.setContent(myRoot);
        myTab.setText(tabName);
        return myTab;
    }

    private void initializePlayer () {
        myCanvas = new GameCanvas(myGUIResources);
        myHUD = new GameHUD(myGUIResources, myCanvas);
        gameSection.getChildren().addAll(myCanvas.createCanvas(), myHUD.createNode());
        gameView = new GameView(gameEngine, myCanvas, myHUD, myScene, this, myGUI);
        gameView.playGame(0);
    }

    private void initializeElements (ResourceBundle elements, ResourceBundle resource) {
        IGUIObject newElement = null;
        Enumeration<String> resourceKeys = elements.getKeys();
        while (resourceKeys.hasMoreElements()) {
            String currentKey = resourceKeys.nextElement();
            String[] keyAndPosition = elements.getObject(currentKey).toString().split(",");
            try {
                newElement = (IGUIObject) Class.forName(PACKAGE_NAME + keyAndPosition[0].trim())
                        .getConstructor(ResourceBundle.class, GameDataSource.class, IGameView.class, PlayerGUI.class)
                        .newInstance(resource, gameData, gameView, myGUI);
                gameElements.add(newElement);
                placeElement(newElement, keyAndPosition[1].trim());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void placeElement (IGUIObject element, String position) {
        try {
            getClass().getDeclaredMethod(position, Node.class).invoke(this, element.createNode());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUISections () {
        gameSection = new VBox();
        configurationPanel = new VBox(Double.valueOf(myGUIResources.getString("ConfigurationPanelSpacing")));
        gameMenu = new VBox(Double.valueOf(myGUIResources.getString("GameMenuSpacing")));
        gamePanel = new VBox(Double.valueOf(myGUIResources.getString("GamePanelSpacing")));
        towerPanel = new VBox(Double.valueOf(myGUIResources.getString("TowerPanelSpacing")));
        this.configurePanels();
    }

    private void placeUISections () {
        myRoot.setCenter(gameSection);
        myRoot.setRight(configurationPanel);
        myRoot.setTop(gameMenu);
        myRoot.setBottom(gamePanel);
        myRoot.setLeft(towerPanel);
    }

    private void configurePanels () {
    	Insets panelInsets = new Insets(
    			Double.valueOf(myGUIResources.getString("TopPadding")), 
    			Double.valueOf(myGUIResources.getString("RightPadding")), 
    			Double.valueOf(myGUIResources.getString("BottomPadding")),
    			Double.valueOf(myGUIResources.getString("LeftPadding")));
        configurationPanel.setAlignment(Pos.TOP_CENTER);
        configurationPanel.setPadding(panelInsets);
        gamePanel.setPadding(panelInsets);
        towerPanel.setPadding(panelInsets);
        gamePanel.getStyleClass().add("hbox");
    }

    protected void updateGameElements () {
        for (IGUIObject object : gameElements) {
            object.updateNode();
        }
    }

    protected void addToTowerPanel (Node element) {
        towerPanel.getChildren().add(element);
    }

    protected void addToTop (Node element) {
        gameMenu.getChildren().add(element);
    }

    protected void addToConfigurationPanel (Node element) {
        configurationPanel.getChildren().add(element);
    }

    protected void removeFromConfigurationPanel (Node element) {
        configurationPanel.getChildren().remove(element);
    }

    protected void addToGamePanel (Node element) {
        gamePanel.getChildren().add(element);
    }
    
    protected IMainView getMainView() {
    	return myMainView;
    }
}