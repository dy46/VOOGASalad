package auth_environment.Models.Interfaces;

import java.util.List;

import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;

public interface IWaveWindowTabModel {

    public Wave createWave(String name, String level, List<String> spawningNames, List<Integer> spawningTimes, List<String> placingNames, int timeBeforeWave);
    
    public List<Unit> unitsFromNames(List<String> names);
    
    public void addSpawningUnit(String name, int time);
    
    public List<String> getEnemyInfo();
    
    public void addSpawningUnits(List<String> names, List<Integer> times);
    
    public void removeSpawningUnit(int index);
    
    public void addSpawningUnit(String name);

    public void addPlacingUnit(String name);

    public void addPlacingUnits(List<String> names);

    public void removePlacingUnit(int index);
    
}
