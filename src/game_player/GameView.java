package game_player;

import java.util.List;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameView implements IGameView{
    
    private AnimationTimer AT;
    private boolean isPlaying;
    private Canvas canvas;
    private GraphicsContext GC;
    private Stage myStage;
    private IPlayerEngineInterface playerEngineInterface;
    
    public GameView(Stage primaryStage) {
        playerEngineInterface = new EngineWorkspace();
        playerEngineInterface.setUpEngine(null);
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
        myStage = primaryStage;
        canvas = new Canvas(500, 500);
        GC = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        isPlaying = true;
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
                   placePath();
                   placeUnit();
               }
            }
        };
        AT.start();
    }

    @Override
    public void toggleGame () {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }

    @Override
    public void placeUnit () {
        List<Enemy> currEnemies = playerEngineInterface.getEnemies();
        for(int i = 0; i < currEnemies.size(); i++) {
            GC.drawImage(new Image(getClass().getClassLoader().getResourceAsStream("enemy.png")), 
                         currEnemies.get(i).getProperties().getPosition().getX(),
                         currEnemies.get(i).getProperties().getPosition().getY());
        }
    }

    @Override
    public void pauseGame () {
        // TODO Auto-generated method stub
    }

}
