package game_player.view;

import java.util.ArrayList;
import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_player.GameDataSource;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class GameView implements IGameView{
	
	private int timer;
	private static final int DEFAULT_UPDATE_SPEED = 1;
	private AnimationTimer AT;
	private boolean timerStatus;
	private boolean isPlaying;
	private GameCanvas canvas;
	private Pane root;
	private Scene myScene;
	private IPlayerEngineInterface playerEngineInterface;
	private GameDataSource gameData;
	private double myUpdateSpeed;
	private double currentSpeed;
	private List<ImageViewPicker> towers;
	private List<ImageViewPicker> enemies;
	private List<ImageViewPicker> projectiles;
	private List<ImageView> paths;
	private List<ImageViewPicker> terrains;
	private PlayerMainTab myTab;
	private List<ImageView> towerTypes;
	private String clickedTower;

	public GameView(IPlayerEngineInterface engine, GameCanvas canvas, Scene scene, PlayerMainTab tab) {
		this.root = canvas.getRoot();
		this.myScene = scene;
		this.playerEngineInterface = engine;	
		gameData = new GameDataSource();
		isPlaying = true;
		this.towers = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.projectiles = new ArrayList<>();
		this.terrains = new ArrayList<>();
		this.towerTypes = new ArrayList<>();
		this.timer = 0;
		this.myUpdateSpeed = DEFAULT_UPDATE_SPEED;
		this.currentSpeed = DEFAULT_UPDATE_SPEED;
		this.paths = new ArrayList<>();
		this.myScene.setOnKeyPressed(e -> setUpKeyPressed(e.getCode()));
		this.root.setOnMouseClicked(e -> {
			playerEngineInterface.addTower(clickedTower, e.getX(), e.getY());
		});
		this.myTab = tab;
	}

	public void setUpKeyPressed(KeyCode code) {
		switch (code) {
		case SPACE:
			toggleGame();
		default:
			//do nothing
		}
	}

	@Override
	public void toggleGame () {
		if(timerStatus) {
			myUpdateSpeed = 0;
			timerStatus = false;
		}
		else {
			myUpdateSpeed = currentSpeed;
			timerStatus = true;
		}
	}

	public void display() {
		playGame(0);
	}

	@Override
	public void playGame (int gameIndex) {
		AT = new AnimationTimer() {            
			public void handle(long currentNanoTime) {
				if(isPlaying) {
					timer++;
					updateEngine();
					placePath();
					placeUnits(playerEngineInterface.getTowers(), towers);
					placeUnits(playerEngineInterface.getTerrains(), terrains);
					placeUnits(playerEngineInterface.getProjectiles(), projectiles);
					placeUnits(playerEngineInterface.getEnemies(), enemies);
					makeTowerPicker();
				}
			}
		};
		AT.start();
		timerStatus = true;
	}

	@Override
	public void restartGame() {
		//restart Game
	}

	private void updateEngine() {
		for (int i = 1; i <= myUpdateSpeed; i++) {
			playerEngineInterface.updateElements();
		}
		myTab.updateGameElements();
	}

	@Override
	public void changeColorScheme (int colorIndex) {
		// TODO Auto-generated method stub  
	}

	@Override
	public void changeGameSpeed (double gameSpeed) {
		this.myUpdateSpeed = gameSpeed;
		this.currentSpeed = gameSpeed;
	}

	public void makeTowerPicker() {
		List<Tower> allTowerTypes = playerEngineInterface.getTowerTypes();
		for(int i = towerTypes.size(); i < allTowerTypes.size(); i++) {
			String name = allTowerTypes.get(i).toString();
			Image img = new Image(name + ".png");
			ImageView imgView = new ImageView(img);
			imgView.setX(100*i);
			imgView.setY(0);
			imgView.setRotate(transformDirection(allTowerTypes.get(i)));
			towerTypes.add(imgView);
			myTab.addToConfigurationPanel(imgView);
			imgView.setOnMouseClicked(e -> {
				clickedTower = name;
			});
		}
	}


	public void placePath () {
		List<Branch> currPaths = playerEngineInterface.getPaths();
		List<Position> allPositions = new ArrayList<>();
		currPaths.stream().forEach(cp -> allPositions.addAll(cp.getAllPositions()));
		for(int i = paths.size(); i < allPositions.size(); i++) {
			Image img = new Image(currPaths.get(0).toString() + ".png");
			ImageView imgView = new ImageView(img);
			imgView.setX(allPositions.get(i).getX() - imgView.getImage().getWidth()/2);
			imgView.setY(allPositions.get(i).getY() - imgView.getImage().getHeight()/2);
			root.getChildren().add(imgView);
			imgView.toFront();
			paths.add(imgView);
		}
	}

	public void placeUnits(List<Unit> list, List<ImageViewPicker> imageViews) {
	        if(list.size() < imageViews.size()) {
	            imageViews.stream().forEach(i -> root.getChildren().remove(i.getImageView()));
	            imageViews.clear();
	        }
		for(int i = imageViews.size(); i < list.size(); i++) {
			Unit u = list.get(i);
			imageViews.add(new ImageViewPicker(u.toString(), u.getNumFrames(), 
					u.getProperties().getState().getValue(), root));
		}
		for(int i = 0; i < list.size(); i++) {
			imageViews.get(i).selectNextImageView(list.get(i), timer);
		}   
	}

	public double transformDirection(Unit u) {
		return -u.getProperties().getVelocity().getDirection() + 90;
	}

	@Override
	public IPlayerEngineInterface getGameEngine() {
		return playerEngineInterface;
	}
}
