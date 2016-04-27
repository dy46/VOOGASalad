package auth_environment;

import java.util.ArrayList;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.paths.MapHandler;
import game_data.GameData;
import game_data.IGameData;
import game_engine.TestingEngineWorkspace;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.libraries.UnitLibrary;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;


public class AuthEnvironment implements IAuthEnvironment {

	private String myName;
	private String mySplashFileName;
	
	private List<Branch> myBranches;
	private List<Level> myLevels;
	private List<Unit> myTowers; 
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors; // Will eventually be replaced with a Library
	private List<Unit> myPlacedUnits; 
	private List<Position> mySpawns;
	private List<Position> myGoals; 
	
	private IGameData myGameData;

	public AuthEnvironment() {
		myBranches = new ArrayList<Branch>();
		myLevels = new ArrayList<Level>();
		myTowers = new ArrayList<Unit>();
		myEnemies = new ArrayList<Unit>();
		myTerrains = new ArrayList<Unit>();
		myProjectiles = new ArrayList<Unit>();
		myAffectors = new ArrayList<Affector>(); 
		myPlacedUnits = new ArrayList<Unit>(); 
		mySpawns = new ArrayList<Position>();
		myGoals = new ArrayList<Position>(); 	
		
		myGameData = new GameData();
	}
	
	public AuthEnvironment(IGameData gameData) {
		myGameData = gameData;
		
		myLevels = myGameData.getLevels();
		myBranches = myGameData.getBranches();
		myAffectors = myGameData.getAffectors();
		myPlacedUnits = myGameData.getPlacedUnits(); 
		

		myTowers = new ArrayList<Unit>();
		myEnemies = new ArrayList<Unit>();
		myTerrains = new ArrayList<Unit>();
		myProjectiles = new ArrayList<Unit>();
		mySpawns = new ArrayList<Position>();
		myGoals = new ArrayList<Position>(); 				
	}

	private void setupDummyValues() {
		TestingEngineWorkspace test = new TestingEngineWorkspace();
		test.setUpEngine(null);
		this.setTerrains(test.getTerrains());
		this.setTowers(test.getTowers());
		this.setLevels(test.getLevels());
		this.setProjectiles(test.getProjectiles());
		this.setAffectors(test.getAffectors());
		this.setEnemies(test.getEnemies());
		MapHandler mh = new MapHandler();
		List<Branch> branches = mh.getEngineBranches();
		this.setEngineBranches(mh.getEngineBranches());
		this.setGoals(mh.getGoals());
		this.setSpawns(mh.getSpawns());
		//		Unit tower = test.getTerrains().get(0); 
		//		UnitView uv = new UnitView(tower, "smackCat.gif"); 
	}

	@Override
	public void setGameName(String name) {
		this.myName = name; 
	}

	@Override
	public String getGameName() {
		return this.myName; 
	}

	@Override
	public void setSplashScreen(String fileName) {
		this.mySplashFileName = fileName;
	}

	@Override
	public String getSplashScreen() {
		return this.mySplashFileName;
	}

	@Override
	public List<Level> getLevels() {
		return this.myLevels;
	}

	@Override
	public void addLevel(Level level) {
		this.myLevels.add(level); 
	}

	@Override
	public List<Unit> getPlacedUnits() {
		return this.myPlacedUnits;
	}

	@Override
	public void placeUnit(Unit unit) {
		this.myPlacedUnits.add(unit); 
	}

	@Override
	public List<Unit> getTowers() {
		return this.myTowers;
	}

	@Override
	public List<Unit> getTerrains() {
		return this.myTerrains;
	}

	@Override
	public List<Unit> getEnemies() {
		return this.myEnemies;
	}

	@Override
	public List<Unit> getProjectiles() {
		return this.myProjectiles;
	}

	@Override
	public List<Affector> getAffectors() {
		return this.myAffectors;
	}

	@Override
	public void setPlacedUnits(List<Unit> units) {
		this.myPlacedUnits = units; 
	}

	@Override
	public void setTowers(List<Unit> towers) {
		this.myTowers = towers; 
	}

	@Override
	public void setTerrains(List<Unit> terrains) {
		this.myTerrains = terrains;
	}

	@Override
	public void setEnemies(List<Unit> enemies) {
		this.myEnemies = enemies; 
	}

	@Override
	public void setProjectiles(List<Unit> projectiles) {
		this.myProjectiles = projectiles; 
	}

	@Override
	public void setAffectors(List<Affector> affectors) {
		this.myAffectors = affectors; 
	}

	@Override
	public List<Position> getGoals() {
		return this.myGoals;
	}

	@Override
	public void setGoals(List<Position> goals) {
		this.myGoals = goals; 
	}

	@Override
	public List<Position> getSpawns() {
		return this.mySpawns;
	}

	@Override
	public void setSpawns(List<Position> spawns) {
		this.mySpawns = spawns; 
	}

	@Override
	public void setLevels(List<Level> levels) {
		this.myLevels = levels; 
	}

	@Override
	public void setVisualBranches(List<Branch> branches) {
		this.myVisualBranches = branches;
	}

	@Override
	public void setGridBranches(List<Branch> gridBranches) {
		myGridBranches = gridBranches;
	}

	@Override
	public List<Branch> getGridBranches() {
		return myGridBranches;
	}
	
    @Override
    public UnitFactory getUnitFactory () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUnitFactory (UnitFactory factory) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FunctionFactory getFunctionFactory () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFunctionFactory (FunctionFactory factory) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AffectorFactory getAffectorFactory () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAffectorFactory (AffectorFactory factory) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void saveGameData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Branch> getBranches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceValidation> getPlaceValidations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WaveGoal getWaveGoal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWaveGoal(WaveGoal waveGoal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScoreUpdate getScoreUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScoreUpdate(ScoreUpdate scoreUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store getStore() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setStore(Store store) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setScore(double score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGameData getGameData() {

		return null;
	}
	
}