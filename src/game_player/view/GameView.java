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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameView implements IGameView{
    
    private int timer;
    private AnimationTimer AT;
    private boolean timerStatus;
    private boolean isPlaying;
    private GameCanvas canvas;
    private Pane root;
    private Stage myStage;
    private PlayerGUI myGUI;
    private IPlayerEngineInterface playerEngineInterface;
	private GameDataSource gameData;
    private List<ImageViewPicker> towers;
    private List<ImageViewPicker> enemies;
    private List<ImageViewPicker> projectiles;
    private List<ImageView> paths;
    private List<ImageView> terrains;
    
    public GameView(GameCanvas canvas) {
    	this.root = canvas.getRoot();
        playerEngineInterface = new EngineWorkspace();
        playerEngineInterface.setUpEngine(null);
        gameData = new GameDataSource();
//        primaryStage.setScene(theScene);
//        canvas = new Canvas(500, 500);
//        canvas.getGraphicsContext2D().drawImage(new Image("background.png"), 0, 0);
//        root.getChildren().add(canvas);
        isPlaying = true;
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.terrains = new ArrayList<>();
        this.timer = 0;
        this.paths = new ArrayList<>();
        this.root.setOnKeyPressed(e -> setUpKeyPressed(e.getCode().toString()));
        this.root.setOnMouseClicked(e -> {
           playerEngineInterface.addTower(e.getX(), e.getY(), 0);
        });
        
        
    }
    
    public void setUpKeyPressed(String code) {
        if(code.equals("SPACE")) {
            toggleGame();
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
//        this.myStage.show();
        playGame(0);
    }
    
    @Override
    public void playGame (int gameIndex) {
        AT = new AnimationTimer() {            
            public void handle(long currentNanoTime) {
               if(isPlaying) {
                   timer++;
                   playerEngineInterface.updateElements();
                   placeUnit();
                   placePath();
                   placeTerrain();
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
    public void changeColorScheme (int colorIndex) {
        // TODO Auto-generated method stub  
    }

    @Override
    public void changeGameSpeed (int gameSpeed) {
        // TODO Auto-generated method stub
    }

    @Override
    public void placeTerrain () {
        List<Unit> currTerrain = playerEngineInterface.getTerrains();
        for(int i = terrains.size(); i < currTerrain.size(); i++) {
            Image img = new Image(currTerrain.get(i).toString() + ".png");
            ImageView imgView = new ImageView(img);
            imgView.setX(currTerrain.get(i).getProperties().getPosition().getX());
            imgView.setY(currTerrain.get(i).getProperties().getPosition().getY());
            root.getChildren().add(imgView);
            imgView.toFront();
            terrains.add(imgView);
        }
    }

    @Override
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
    
    @Override
    public void placeUnit () {
        List<Unit> currEnemies = playerEngineInterface.getEnemies();
        placeUnits(currEnemies, enemies);
        placeUnits(playerEngineInterface.getProjectiles(), projectiles);
        placeUnits(playerEngineInterface.getTowers(), towers);
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
