package game_player;

import java.util.ArrayList;
import java.util.List;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Unit;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class GameView implements IGameView{
    
    private int timer;
    private AnimationTimer AT;
    private boolean isPlaying;
    private Canvas canvas;
    private Group root;
    private Stage myStage;
    private IPlayerEngineInterface playerEngineInterface;
    private List<ImageViewPicker> units;
    
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
        this.units = new ArrayList<>();
        this.timer = 0;
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
                   timer++;
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
        List<Unit> currUnits = playerEngineInterface.getUnits();
        for(int i = units.size(); i < currUnits.size(); i++) {
            Unit u = currUnits.get(i);
            units.add(new ImageViewPicker(u.toString(), u.getNumFrames(), 
                                          u.getProperties().getState().getValue(), root));
        }
        for(int i = 0; i < currUnits.size(); i++) {
            units.get(i).selectNextImageView(currUnits.get(i), timer);
        }
    }

    @Override
    public void pauseGame () {
        // TODO Auto-generated method stub
    }

}
