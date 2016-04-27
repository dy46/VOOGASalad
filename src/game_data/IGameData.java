package game_data;

import java.util.List;

import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

public interface IGameData {
	
    public List<Level> getLevels();

    public List<Branch> getBranches();
    
    public List<Unit> getPlacedUnits();

    public List<Affector> getAffectors();

    public List<PlaceValidation> getPlaceValidations();

   
    public WaveGoal getWaveGoal();
    public void setWaveGoal(WaveGoal waveGoal);
    
    public ScoreUpdate getScoreUpdate();
    public void setScoreUpdate(ScoreUpdate scoreUpdate);

    public Store getStore();
    public void setStore(Store store);
    
    public double getScore();
    public void setScore(double score);    
}
