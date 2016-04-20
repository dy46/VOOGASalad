package game_player.view;

import game_player.GameDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import game_engine.games.GameEngineInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayerMainTab implements IPlayerTab{
	private static final int TOP_PADDING = 5;
	private static final int RIGHT_PADDING = 10;
	private static final int BOTTOM_PADDING = 5;
	private static final int LEFT_PADDING = 10;
	private static final String GUI_ELEMENTS = "GUIElements";
	private static final String PACKAGE_NAME = "game_player.view.";
	private static final int PANEL_PADDING = 10;
	private Tab myTab;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private ResourceBundle elementsResources;
	private GameDataSource gameData;
	private List<IGUIObject> gameElements;
	private IGameView gameView;
	private GameCanvas myCanvas;
	private Scene myScene;
	private String tabName;
	
	private VBox gameSection;
	private VBox configurationPanel;
	private VBox gameMenu;
	private VBox gamePanel;
	
	private GameEngineInterface gameEngine;
	
	public PlayerMainTab(GameEngineInterface engine, ResourceBundle r, Scene scene, String tabName) {
		this.gameEngine = engine;
		this.myResources = r;
		this.gameElements = new ArrayList<>();
		this.elementsResources = ResourceBundle.getBundle(GUI_ELEMENTS);
		this.gameData = new GameDataSource();
		this.myScene = scene;
		this.tabName = tabName;
		gameData.setDoubleValue("High Score", 0);
	}
	
	@Override
	public Tab getTab() {
		myTab = new Tab();
		myRoot = new BorderPane();
		
		createUISections();
		initializeCanvas();
		initializeElements();
		placeUISections();
		
		myTab.setContent(myRoot);
		myTab.setText(tabName);
		return myTab;
	}
	
	private void initializeCanvas() {
		myCanvas = new GameCanvas(myResources);
		gameSection.getChildren().add(myCanvas.createCanvas());
		gameView = new GameView(gameEngine, myCanvas, myScene, this);
		gameView.playGame(0);
	}
	
	private void initializeElements() {
		IGUIObject newElement = null;
		Enumeration<String> resourceKeys = elementsResources.getKeys();
		while (resourceKeys.hasMoreElements()) {
			String currentKey = resourceKeys.nextElement();
			String[] keyAndPosition = elementsResources.getObject(currentKey).toString().split(",");
			try {
				newElement = (IGUIObject) Class.forName(PACKAGE_NAME + keyAndPosition[0].trim())
						.getConstructor(ResourceBundle.class, GameDataSource.class, IGameView.class)
						.newInstance(myResources, gameData, gameView);
				gameElements.add(newElement);

				placeElement(newElement, keyAndPosition[1].trim());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void placeElement(IGUIObject element, String position) {
		try{
			getClass().getDeclaredMethod(position, Node.class).invoke(this, element.createNode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createUISections() {
		gameSection = new VBox(PANEL_PADDING);
		configurationPanel = new VBox(PANEL_PADDING);
		gameMenu = new VBox(PANEL_PADDING);
		gamePanel = new VBox(PANEL_PADDING);
		this.configurePanels();
	}
	
	private void placeUISections() {
		myRoot.setCenter(gameSection);
		myRoot.setRight(configurationPanel);
		myRoot.setTop(gameMenu);
		myRoot.setBottom(gamePanel);
	}
	
	private void configurePanels() {
//		Label configurationLabel = new Label(myResources.getString("Configuration"));
//		configurationLabel.setFont(new Font("Arial", 20));
//		configurationLabel.getStyleClass().add("label-header");
		configurationPanel.setAlignment(Pos.TOP_CENTER);
//		configurationPanel.getChildren().add(configurationLabel);
//		configurationPanel.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
		configurationPanel.getStyleClass().add("vbox");
		gamePanel.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
		gamePanel.getStyleClass().add("hbox");
	}
	
	protected void updateGameElements() {
		for (IGUIObject object: gameElements) {
			object.updateNode();
		}
	}
	
	protected void addToTop(Node element) {
		gameMenu.getChildren().add(element);
	}
	
	protected void addToConfigurationPanel(Node element) {
		configurationPanel.getChildren().add(element);
	}
	
	protected void addToGamePanel(Node element) {
		gamePanel.getChildren().add(element);
	}
}
