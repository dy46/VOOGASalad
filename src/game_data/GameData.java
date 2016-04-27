package game_data;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

public class GameData implements IGameData {
	private List<Level> myLevels = new ArrayList<Level>();
	private List<Branch> myBranches = new ArrayList<Branch>();
	private List<Unit> myPlacedUnits = new ArrayList<Unit>();
	private List<Affector> myAffectors = new ArrayList<Affector>();
	private List<PlaceValidation> myPlaceValidations = new ArrayList<PlaceValidation>();
	private WaveGoal myWaveGoal;
	private ScoreUpdate myScoreUpdate;
	private Store myStore;
	private double myScore;
	
	// GETTERS
	@Override
	public List<Level> getLevels() {
		return myLevels;
	}

	@Override
	public List<Branch> getBranches() {
		return myBranches;
	}

	@Override
	public List<Unit> getPlacedUnits() {
		return myPlacedUnits;
	}

	@Override
	public List<Affector> getAffectors() {
		return myAffectors;
	}

	@Override
	public List<PlaceValidation> getPlaceValidations() {
		return myPlaceValidations;
	}

	@Override
	public WaveGoal getWaveGoal() {
		return myWaveGoal;
	}

	@Override
	public ScoreUpdate getScoreUpdate() {
		return myScoreUpdate;
	}

	@Override
	public Store getStore() {
		return myStore;
	}

	@Override
	public double getScore() {
		return myScore;
	}

	// SETTERS
	@Override
    public void setWaveGoal(WaveGoal waveGoal) {
    	myWaveGoal = waveGoal;
    }
    
	@Override
    public void setScoreUpdate(ScoreUpdate scoreUpdate) {
    	myScoreUpdate = scoreUpdate;
    }

	@Override
    public void setStore(Store store) {
    	myStore = store;
    }
    
	@Override
    public void setScore(double score) {
    	myScore = score;
    }
}
