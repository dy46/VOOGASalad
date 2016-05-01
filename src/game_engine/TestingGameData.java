package game_engine;

import java.util.ArrayList;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.paths.MapHandler;
import game_data.IGameData;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.place_validations.EnemySpawnPointPlaceValidation;
import game_engine.place_validations.PlaceValidation;
import game_engine.place_validations.TowerPlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.EnemyDeathScoreUpdate;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.EnemyNumberWaveGoal;
import game_engine.wave_goals.WaveGoal;


public class TestingGameData implements IAuthEnvironment{

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
        TestingEngineWorkspace engine = new TestingEngineWorkspace();
//        PlatformEngineWorkspace engine = new PlatformEngineWorkspace();
        engine.setUpEngine(this);
        this.myPlacedUnits = engine.getAllUnits();
        this.myStore = engine.getStore();
        System.out.println(myStore.getTowerList());
        this.myLevels = engine.getLevels();
        this.myBranches = engine.getBranches();
        this.myAffectors = engine.getAffectors();
        this.myWaveGoal = new EnemyNumberWaveGoal();
        this.myScoreUpdate = new EnemyDeathScoreUpdate();
    }

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

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSplashScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSplashScreen(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> getTowers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTowers(List<Unit> towers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> getTerrains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTerrains(List<Unit> terrains) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> getEnemies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnemies(List<Unit> enemies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> getProjectiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProjectiles(List<Unit> projectiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Position> getGoals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGoals(List<Position> goals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Position> getSpawns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpawns(List<Position> spawns) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FunctionFactory getFunctionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFunctionFactory(FunctionFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLevels(List<Level> levels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBranches(List<Branch> branches) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AffectorFactory getAffectorFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAffectorFactory(AffectorFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlaceValidations(List<PlaceValidation> placeValidations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnitFactory getUnitFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUnitFactory(UnitFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapHandler getMapHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMapHandler(MapHandler mapHandler) {
		// TODO Auto-generated method stub
		
	}

}
