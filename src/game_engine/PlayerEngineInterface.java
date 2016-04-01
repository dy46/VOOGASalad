package game_engine;

import java.util.List;

public class PlayerEngineInterface implements IPlayerEngineInterface {

    @Override
    public List<String> saveGame () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void playLevel (int levelNumber) {
        // TODO Auto-generated method stub 
    }

    @Override
    public void playWave (int waveNumber) {
        // TODO Auto-generated method stub 
    }

    @Override
    public void updateElements () {
        // TODO Auto-generated method stub 
    }

    @Override
    public List<Double> getGameStatus () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTower (int towerIndex) {
        // TODO Auto-generated method stub
    }

    @Override
    public void modifyTower (int activeTowerIndex, List<Double> changes) {
        // TODO Auto-generated method stub   
    }

    @Override
    public void setUpEngine (List<String> fileNames) {
        // TODO Auto-generated method stub  
    }

}
