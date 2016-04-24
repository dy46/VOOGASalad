package game_player.view;

import game_player.GameDataSource;

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

public class PlayerMainTab implements IPlayerTab{
	private static final int TOP_PADDING = 0;
	private static final int RIGHT_PADDING = 10;
	private static final int BOTTOM_PADDING = 5;
	private static final int LEFT_PADDING = 10;
	private static final String GUI_ELEMENTS = "game_player/resources/GUIElements";
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
	private GameHUD myHUD;
	private Scene myScene;
	private String tabName;
	
	private VBox gameSection;
	private VBox configurationPanel;
	private VBox gameMenu;
	private VBox gamePanel;
	private VBox towerPanel;
	
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
		myHUD = new GameHUD(myResources);
		gameSection.getChildren().addAll(myCanvas.createCanvas(), myHUD.createNode());
		gameView = new GameView(gameEngine, myCanvas, myHUD, myScene, this);
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
		gameSection = new VBox();
		configurationPanel = new VBox(PANEL_PADDING);
		gameMenu = new VBox(PANEL_PADDING);
		gamePanel = new VBox(PANEL_PADDING);
		towerPanel = new VBox(PANEL_PADDING);
		this.configurePanels();
	}
	
	private void placeUISections() {
		myRoot.setCenter(gameSection);
		myRoot.setRight(configurationPanel);
		myRoot.setTop(gameMenu);
		myRoot.setBottom(gamePanel);
		myRoot.setLeft(towerPanel);
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
	
	protected void addToTowerPanel(Node element) {
		towerPanel.getChildren().add(element);
	}
	
	protected void addToTop(Node element) {
		gameMenu.getChildren().add(element);
	}
	
	protected void addToConfigurationPanel(Node element) {
		configurationPanel.getChildren().add(element);
	}
	
	public void removeFromConfigurationPanel(Node element) {
	    configurationPanel.getChildren().remove(element);
	}
	
	protected void addToGamePanel(Node element) {
		gamePanel.getChildren().add(element);
	}
}
