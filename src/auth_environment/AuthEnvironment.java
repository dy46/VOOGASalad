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
	
//	private List<Level> myLevels = new ArrayList<Level>();
//	private List<Branch> myBranches = new ArrayList<Branch>();
//	private List<Unit> myPlacedUnits = new ArrayList<Unit>(); 
//	private List<PlaceValidation> myPlaceValidations = new ArrayList<PlaceValidation>();
//	private List<Affector> myAffectors = new ArrayList<Affector>(); // Will eventually be replaced with a Library
//	private ScoreUpdate myScoreUpdate;
//	private WaveGoal myWaveGoal;
//	private Store myStore;
//	private double myScore;
//	
//	private AffectorFactory myAffectorFactory;
//	private UnitFactory myUnitFactory;
	
	private List<Unit> myTowers = new ArrayList<Unit>();
	private List<Unit> myEnemies = new ArrayList<Unit>();
	private List<Unit> myTerrains = new ArrayList<Unit>();
	private List<Unit> myProjectiles = new ArrayList<Unit>();
	private List<Position> mySpawns = new ArrayList<Position>();
	private List<Position> myGoals = new ArrayList<Position>();
	private FunctionFactory myFunctionFactory;
	
	private IGameData myGameData = new GameData();

	public AuthEnvironment() { 
		
	}
	public AuthEnvironment(IGameData gameData) {
		myGameData = gameData;
		
		myTowers = myGameData.getUnitFactory().getUnitLibrary().getUnits();
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
	public String getSplashScreen() {
		return this.mySplashFileName;
	}
	@Override
	public void setSplashScreen(String fileName) {
		this.mySplashFileName = fileName;
	}

	@Override
	public List<Position> getGoals() {
		return myGoals;
	}
	@Override
	public void setGoals(List<Position> goals) {
		myGoals = goals;
	}
	
	@Override
	public List<Position> getSpawns() {
		return mySpawns;
	}
	@Override
	public void setSpawns(List<Position> spawns) {
		mySpawns = spawns;
	}
	
	@Override
	public List<Unit> getTowers() {
		return myTowers;
	}
	@Override
	public void setTowers(List<Unit> towers) {
		myTowers = towers;
	}
	
	@Override
	public List<Unit> getTerrains() {
		return myTerrains;
	}
	@Override
	public void setTerrains(List<Unit> terrains) {
		myTerrains = terrains;
	}
	
	@Override
	public List<Unit> getEnemies() {
		return myEnemies;
	}
	@Override
	public void setEnemies(List<Unit> enemies) {
		myEnemies = enemies;
	}
	
	@Override
	public List<Unit> getProjectiles() {
		return myProjectiles;
	}
	@Override
	public void setProjectiles(List<Unit> projectiles) {
		myProjectiles = projectiles;
	}

	@Override
	public FunctionFactory getFunctionFactory() {
		return myFunctionFactory;
	}
	@Override
	public void setFunctionFactory(FunctionFactory factory) {
		myFunctionFactory = factory;
	}
	
	@Override
	public List<Level> getLevels() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setLevels(List<Level> levels) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Branch> getBranches() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setBranches(List<Branch> branches) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Unit> getPlacedUnits() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPlacedUnits(List<Unit> units) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Affector> getAffectors() {
		// TODO Auto-generated method stub
		return null;
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
	public List<PlaceValidation> getPlaceValidations() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPlaceValidations(List<PlaceValidation> placeValidations) {
		// TODO Auto-generated method stub
		
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
	public UnitFactory getUnitFactory() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUnitFactory(UnitFactory factory) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public IGameData getGameData() {
		// TODO Auto-generated method stub
		return null;
	}

	
}