package game_player;

import game_engine.IPlayerEngineInterface;
import game_engine.PlayerEngineInterface;
import javafx.animation.AnimationTimer;

public class GameView implements IGameView{
    
    AnimationTimer AT;
    boolean isPlaying;
    IPlayerEngineInterface playerEngineInterface;
    
    public GameView() {
        playerEngineInterface = new PlayerEngineInterface();
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
        // TODO Auto-generated method stub
    }

}
