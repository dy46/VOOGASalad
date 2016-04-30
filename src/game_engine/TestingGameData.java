package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_data.IGameData;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.place_validations.EnemySpawnPointPlaceValidation;
import game_engine.place_validations.PlaceValidation;
import game_engine.place_validations.TowerPlaceValidation;
import game_engine.score_updates.EnemyDeathScoreUpdate;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.EnemyNumberWaveGoal;
import game_engine.wave_goals.WaveGoal;


public class TestingGameData{

    private List<Level> myLevels;
    private List<PlaceValidation> myPlaceValidations;
    private WaveGoal myWaveGoal;
    private ScoreUpdate myScoreUpdate;
    private List<Affector> myAffectors;
    private List<Unit> myPlacedUnits;
    private List<Branch> myBranches;
    private double myScore;
    private Store myStore;

    public TestingGameData () {
        this.myScore = 0;
        this.myPlaceValidations = new ArrayList<>();
        this.myPlaceValidations.add(new EnemySpawnPointPlaceValidation());
        this.myPlaceValidations.add(new TowerPlaceValidation());
        //TestingEngineWorkspace engine = new TestingEngineWorkspace();
        PlatformEngineWorkspace engine = new PlatformEngineWorkspace();
        engine.setUpEngine(null);
        this.myPlacedUnits = engine.getAllUnits();
        this.myStore = engine.getStore();
        this.myLevels = engine.getLevels();
        this.myBranches = engine.getBranches();
        this.myAffectors = engine.getAffectors();
        this.myWaveGoal = new EnemyNumberWaveGoal();
        this.myScoreUpdate = new EnemyDeathScoreUpdate();
    };

    public List<Level> getLevels () {
        return myLevels;
    }

    public void addLevel (Level level) {
        this.myLevels.add(level);
    }

    public List<Unit> getPlacedUnits () {
        return this.myPlacedUnits;
    }

    public void placeUnit (Unit unit) {
        this.myPlacedUnits.add(unit);
    }
    
    public void setPlacedUnits (List<Unit> units) {
        this.myPlacedUnits = units;
    }
    
    public List<Affector> getAffectors () {
        return this.myAffectors;
    }

    public void setAffectors (List<Affector> affectors) {
        this.myAffectors = affectors;
    }

    public List<Branch> getBranches () {
        return myBranches;
    }

    public List<PlaceValidation> getPlaceValidations () {
        return myPlaceValidations;
    }

    public WaveGoal getWaveGoal () {
        return myWaveGoal;
    }

    public ScoreUpdate getScoreUpdate () {
        return myScoreUpdate;
    }

    public Store getStore () {
        return myStore;
    }
    
	public void setStore(Store store) {
		myStore = store;
	}

    public double getScore () {
        return myScore;
    }

	public void setWaveGoal(WaveGoal waveGoal) {
		myWaveGoal = waveGoal;
	}

	public void setScoreUpdate(ScoreUpdate scoreUpdate) {
		myScoreUpdate = scoreUpdate;
	}

	public void setScore(double score) {
		myScore = score;
	}

}
