package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.paths.MapHandler;
import game_engine.TestingEngineWorkspace;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This class holds the highest level of Auth Environment backend data. Most important is a single instance
 * of IEngineWorkspace.java (all of our data). 
 * 
 * This class should ALSO implement the Game Player's Interface once Auth testing is complete.
 */

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This class holds the highest level of Auth Environment backend data. Most important is a single instance
 * of IEngineWorkspace.java (all of our data). 
 * 
 * This class should ALSO implement the Game Player's Interface once Auth testing is complete.
 */

public class SampleAuthData implements IAuthEnvironment {

	private String myName;
	private String mySplashFileName;
	private List<Level> myLevels;
	private List<Unit> myTowers; // TODO: remove 
	private List<Unit> myEnemies; // TODO: remove
	private List<Unit> myTerrains; // TODO: remove
	private List<Unit> myProjectiles; // TODO: remove
	private List<Unit> myPlacedUnits; // TODO: remove
	private MapHandler myMapHandler; 
	private List<PlaceValidation> myPlaceValidations; 
	private WaveGoal myWaveGoal;
	private ScoreUpdate myScoreUpdate;
	private Store myStore; 
	
	// TODO: Factory class variables... remove eventually
	private UnitFactory myUnitFactory; 
	private FunctionFactory myFunctionFactory; 
	private AffectorFactory myAffectorFactory;

	public SampleAuthData() {
		this.myName = "sampleGame";
		this.mySplashFileName = "smackCat.gif";
		this.myLevels = new ArrayList<>();
		this.myTowers = new ArrayList<>();
		this.myEnemies = new ArrayList<>();
		this.myTerrains = new ArrayList<>();
		this.myProjectiles = new ArrayList<>();
		this.myPlacedUnits = new ArrayList<>(); 
		this.myPlaceValidations = new ArrayList<>(); 
		this.myUnitFactory = new UnitFactory();
		this.myFunctionFactory = new FunctionFactory(); 
		this.myAffectorFactory = new AffectorFactory(this.myFunctionFactory);
		this.myMapHandler = new MapHandler(); 
		this.setupDummyValues();
	}

	private void setupDummyValues() {
		TestingEngineWorkspace test = new TestingEngineWorkspace();
		test.setUpEngine(this);
		this.myStore = new Store(1000);
//		this.setTerrains(test.getTerrains());
//		this.setTowers(test.getTowers());
//		this.setLevels(test.getLevels());
//		this.setProjectiles(test.getProjectiles());
//		this.setAffectors(test.getAffectors());
//		this.setEnemies(test.getEnemies());
//		MapHandler mh = new MapHandler();
//		this.myBranches = mh.getEngineBranches();
//		List<Branch> branches = mh.getEngineBranches();
//		this.setEngineBranches(mh.getEngineBranches());
//		this.setGoals(mh.getGoals());
//		this.setSpawns(mh.getSpawns());
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
		return this.myAffectorFactory.getAffectorLibrary().getAffectors();
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
	}

	@Override
	public List<Branch> getEngineBranches() {
		return this.myMapHandler.getEngineBranches();
	}

	@Override
	public List<Position> getGoals() {
		return this.myMapHandler.getGoals();
	}
	
	@Override
	public List<Position> getSpawns() {
		return this.myMapHandler.getSpawns();
	}

	@Override
	public void setLevels(List<Level> levels) {
		this.myLevels = levels; 
	}

	@Override
	public UnitFactory getUnitFactory() {
		return this.myUnitFactory;
	}

	@Override
	public void setUnitFactory(UnitFactory factory) {
		this.myUnitFactory = factory; 
	}

	@Override
	public FunctionFactory getFunctionFactory() {
		return this.myFunctionFactory;
	}

	@Override
	public void setFunctionFactory(FunctionFactory factory) {
		this.myFunctionFactory = factory; 
	}

	@Override
	public AffectorFactory getAffectorFactory() {
		return this.myAffectorFactory; 
	}

	@Override
	public void setAffectorFactory(AffectorFactory factory) {
		this.myAffectorFactory = factory; 
	}

	@Override
	public void setWaveGoal(WaveGoal goal) {
		this.myWaveGoal = goal;
	}

	@Override
	public WaveGoal getWaveGoal() {
		return this.myWaveGoal;
	}

	@Override
	public void setScoreUpdate(ScoreUpdate update) {
		this.myScoreUpdate = update; 
	}

	@Override
	public ScoreUpdate getScoreUpdate() {
		return this.myScoreUpdate;
	}

	@Override
	public Store getStore() {
		return this.myStore;
	}

	@Override
	public List<PlaceValidation> getPlaceValidations() {
		return this.myPlaceValidations;
	}

	@Override
	public void setMapHandler(MapHandler mh) {
		myMapHandler = mh;
	}

	@Override
	public MapHandler getMapHandler() {
		return myMapHandler; 
	}
	
}