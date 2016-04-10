package game_player;

import java.util.ArrayList;
import java.util.List;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Path;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameView implements IGameView{
    
    private int timer;
    private AnimationTimer AT;
    private boolean timerStatus;
    private boolean isPlaying;
    private Canvas canvas;
    private Group root;
    private Stage myStage;
    private IPlayerEngineInterface playerEngineInterface;
    private List<ImageViewPicker> towers;
    private List<ImageViewPicker> enemies;
    private List<ImageViewPicker> projectiles;
    private List<ImageView> paths;
    
    public GameView(Stage primaryStage) {
        playerEngineInterface = new EngineWorkspace();
        playerEngineInterface.setUpEngine(null);
        root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
        myStage = primaryStage;
        canvas = new Canvas(500, 500);
        root.getChildren().add(canvas);
        isPlaying = true;
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.timer = 0;
        this.paths = new ArrayList<>();
        root.getScene().setOnKeyPressed(e -> setUpKeyPressed(e.getCode().toString()));
    }
    
    public void setUpKeyPressed(String code) {
        if(code.equals("SPACE")) {
            toggleGame();
        }
        if(code.equals("ENTER")) {
            
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
        this.myStage.show();
        playGame(0);
    }
    
    @Override
    public void playGame (int gameIndex) {
        AT = new AnimationTimer() {            
            public void handle(long currentNanoTime) {
               if(isPlaying) {
                   playerEngineInterface.updateElements();
                   placeTerrain();
                   placeUnit();
                   placePath();
                   timer++;
                   System.out.println(playerEngineInterface.getLives());
                   if(playerEngineInterface.getLives() == 0) {
                       timerStatus = false;
                       AT.stop();
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
        // TODO Auto-generated method stub
    }

    @Override
    public void placePath () {
        List<Path> currPaths = playerEngineInterface.getPaths();
        List<Position> allPositions = new ArrayList<>();
        currPaths.stream().forEach(cp -> allPositions.addAll(cp.getAllPositions()));
        for(int i = paths.size(); i < allPositions.size(); i++) {
            Image img = new Image(getClass().getClassLoader().getResourceAsStream(currPaths.get(0).toString() + ".png"));
            ImageView imgView = new ImageView(img);
            imgView.setX(allPositions.get(i).getX() - imgView.getImage().getWidth()/2);
            imgView.setY(allPositions.get(i).getY() - imgView.getImage().getHeight()/2);
            root.getChildren().add(imgView);
            imgView.toBack();
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

    
    public void placeUnits(List<Unit> currUnits, List<ImageViewPicker> imageViews) {
        for(int i = imageViews.size(); i < currUnits.size(); i++) {
            Unit u = currUnits.get(i);
            imageViews.add(new ImageViewPicker(u.toString(), u.getNumFrames(), 
                                          u.getProperties().getState().getValue(), root));
        }
        for(int i = 0; i < currUnits.size(); i++) {
            imageViews.get(i).selectNextImageView(currUnits.get(i), timer);
        }   
    }

}
