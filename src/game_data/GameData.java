package game_data;

import java.util.ArrayList;
import java.util.List;

import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.UnitFactory;
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
	private List<PlaceValidation> myPlaceValidations = new ArrayList<PlaceValidation>();
	private WaveGoal myWaveGoal;
	private ScoreUpdate myScoreUpdate;
	private Store myStore;
	private double myScore;
	
	private AffectorFactory myAffectorFactory;
	private UnitFactory myUnitFactory;
	
	@Override
	public List<Level> getLevels() {
		return myLevels;
	}
	@Override
	public void setLevels(List<Level> levels) {
		myLevels = levels;
	}

	@Override
	public List<Branch> getBranches() {
		return myBranches;
	}
	@Override
	public void setBranches(List<Branch> branches) {
		myBranches = branches;
	}
	
	@Override
	public List<Unit> getPlacedUnits() {
		return myPlacedUnits;
	}
	@Override
	public void setPlacedUnits(List<Unit> placedUnits) {
		myPlacedUnits = placedUnits;
	}

	@Override
	public List<Affector> getAffectors() {
		return myAffectorFactory == null? null : myAffectorFactory.getAffectorLibrary().getAffectors();
	}
	@Override
	public AffectorFactory getAffectorFactory() {
		return myAffectorFactory;
	}
	@Override
	public void setAffectorFactory(AffectorFactory affectorFactory) {
		myAffectorFactory = affectorFactory;
	}

	@Override
	public List<PlaceValidation> getPlaceValidations() {
		return myPlaceValidations;
	}
	@Override
	public void setPlaceValidations(List<PlaceValidation> placeValidations) {
		myPlaceValidations = placeValidations;
	}

	@Override
	public WaveGoal getWaveGoal() {
		return myWaveGoal;
	}
	@Override
    public void setWaveGoal(WaveGoal waveGoal) {
    	myWaveGoal = waveGoal;
    }

	@Override
	public ScoreUpdate getScoreUpdate() {
		return myScoreUpdate;
	}
	@Override
    public void setScoreUpdate(ScoreUpdate scoreUpdate) {
    	myScoreUpdate = scoreUpdate;
    }

	@Override
	public Store getStore() {
		return myStore;
	}
	@Override
    public void setStore(Store store) {
    	myStore = store;
    }
   
	@Override
	public double getScore() {
		return myScore;
	}
	@Override
    public void setScore(double score) {
    	myScore = score;
    }
 
	@Override
	public UnitFactory getUnitFactory() {
		return myUnitFactory;
	}
	@Override
	public void setUnitFactory(UnitFactory unitFactory) {
		myUnitFactory = unitFactory;
	}
}
