package game_data;

import java.util.List;

import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

public interface IGameData {
    public List<Level> getLevels();
    public void setLevels(List<Level> levels);
    
    public List<Unit> getPlacedUnits();
    public void setPlacedUnits(List<Unit> units);

    public List<Affector> getAffectors();
    public AffectorFactory getAffectorFactory();
    public void setAffectorFactory(AffectorFactory affectorFactory);

    public List<PlaceValidation> getPlaceValidations();
    public void setPlaceValidations(List<PlaceValidation> placeValidations);
    
    public WaveGoal getWaveGoal();
    public void setWaveGoal(WaveGoal waveGoal);
    
    public ScoreUpdate getScoreUpdate();
    public void setScoreUpdate(ScoreUpdate scoreUpdate);

    public Store getStore();
    public void setStore(Store store);
    
    public double getScore();
    public void setScore(double score);    
    
    public UnitFactory getUnitFactory();
	public void setUnitFactory(UnitFactory unitFactory);
}
