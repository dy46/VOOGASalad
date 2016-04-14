package game_player.view;

import java.util.ArrayList;
import java.util.List;
import auth_environment.backend.ISelector;
import auth_environment.backend.SelectorModel;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Path;
import game_engine.game_elements.Projectile;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import game_player.GameDataSource;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameView implements IGameView{
    
    private static final int DEFAULT_UPDATE_SPEED = 2;
	private int timer;
    private AnimationTimer AT;
    private boolean timerStatus;
    private boolean isPlaying;
    private GameCanvas canvas;
    private Pane root;
    private Scene myScene;
    private IPlayerEngineInterface playerEngineInterface;
	private GameDataSource gameData;
	private double myUpdateSpeed;
    private List<ImageViewPicker> towers;
    private List<ImageViewPicker> enemies;
    private List<ImageViewPicker> projectiles;
    private List<ImageView> paths;
    private List<ImageViewPicker> terrains;
    
    public GameView(GameCanvas canvas, Scene scene) {
    	this.root = canvas.getRoot();
    	this.myScene = scene;
        playerEngineInterface = new EngineWorkspace();
        playerEngineInterface.setUpEngine(null);
        gameData = new GameDataSource();
        isPlaying = true;
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.terrains = new ArrayList<>();
        this.timer = 0;
        this.myUpdateSpeed = DEFAULT_UPDATE_SPEED;
        this.paths = new ArrayList<>();
        this.myScene.setOnKeyPressed(e -> setUpKeyPressed(e.getCode()));
        this.root.setOnMouseClicked(e -> {
           playerEngineInterface.addTower(e.getX(), e.getY(), 0);
        });
        
        
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
            AT.stop();
            timerStatus = false;
        }
        else {
            AT.start();
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
                   placeUnits(playerEngineInterface.getTerrains(), terrains);
                   placeUnits(playerEngineInterface.getEnemies(), enemies);
                   placeUnits(playerEngineInterface.getProjectiles(), projectiles);
                   placeUnits(playerEngineInterface.getTowers(), towers);
                   if(playerEngineInterface.getLives() < 0) {
                       timerStatus = false;
                       playerEngineInterface.clearProjectiles();
                   }
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
    }

    @Override
    public void changeColorScheme (int colorIndex) {
        // TODO Auto-generated method stub  
    }

    @Override
    public void changeGameSpeed (double gameSpeed) {
        this.myUpdateSpeed = gameSpeed;
    }


    public void placePath () {
        List<Path> currPaths = playerEngineInterface.getPaths();
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
        for(int i = imageViews.size(); i < list.size(); i++) {
            Unit u = list.get(i);
            imageViews.add(new ImageViewPicker(u.toString(), u.getNumFrames(), 
                                          u.getProperties().getState().getValue(), root));
        }
        for(int i = 0; i < list.size(); i++) {
            imageViews.get(i).selectNextImageView(list.get(i), timer);
        }   
    }

}
