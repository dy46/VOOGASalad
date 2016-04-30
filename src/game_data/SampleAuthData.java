//package game_data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import auth_environment.IAuthEnvironment;
//import auth_environment.paths.MapHandler;
//import game_engine.TestingEngineWorkspace;
//import game_engine.affectors.Affector;
//import game_engine.factories.AffectorFactory;
//import game_engine.factories.FunctionFactory;
//import game_engine.factories.UnitFactory;
//import game_engine.game_elements.Branch;
//import game_engine.game_elements.Level;
//import game_engine.game_elements.Unit;
//import game_engine.libraries.UnitLibrary;
//import game_engine.properties.Position;
//
///**
// * Created by BrianLin on 4/19/16
// * Team member responsible: Brian
// *
// * This class holds the highest level of Auth Environment backend data. Most important is a single instance
// * of IEngineWorkspace.java (all of our data). 
// * 
// * This class should ALSO implement the Game Player's Interface once Auth testing is complete.
// */
//
///**
// * Created by BrianLin on 4/19/16
// * Team member responsible: Brian
// *
// * This class holds the highest level of Auth Environment backend data. Most important is a single instance
// * of IEngineWorkspace.java (all of our data). 
// * 
// * This class should ALSO implement the Game Player's Interface once Auth testing is complete.
// */
//
//public class SampleAuthData implements IAuthEnvironment {
//
//	private String myName;
//	private String mySplashFileName;
//	private List<Branch> myEngineBranches;
//	private List<Level> myLevels;
//	private List<Unit> myTowers; 
//	private List<Unit> myEnemies;
//	private List<Unit> myTerrains;
//	private List<Unit> myProjectiles; 
//	private List<Affector> myAffectors; // Will eventually be replaced with a Library
//	private List<Unit> myPlacedUnits;  
//
//	public SampleAuthData() {
//		this.myName = "sampleGame";
//		this.mySplashFileName = "smackCat.gif";
//		this.myEngineBranches = new ArrayList<>();
//		this.myLevels = new ArrayList<>();
//		this.myTowers = new ArrayList<>();
//		this.myEnemies = new ArrayList<>();
//		this.myTerrains = new ArrayList<>();
//		this.myProjectiles = new ArrayList<>();
//		this.myAffectors = new ArrayList<>(); 
//		this.myPlacedUnits = new ArrayList<>(); 
//		this.setupDummyValues();
//	}
//
//	private void setupDummyValues() {
//		TestingEngineWorkspace test = new TestingEngineWorkspace();
//		test.setUpEngine(null);
//		this.setTerrains(test.getTerrains());
//		this.setTowers(test.getTowers());
//		this.setLevels(test.getLevels());
//		this.setProjectiles(test.getProjectiles());
//		this.setAffectors(test.getAffectors());
//		this.setEnemies(test.getEnemies());
//		MapHandler mh = new MapHandler();
//		List<Branch> branches = mh.getEngineBranches();
//		this.setEngineBranches(mh.getEngineBranches());
//		this.setGoals(mh.getGoals());
//		this.setSpawns(mh.getSpawns());
//		//		Unit tower = test.getTerrains().get(0); 
//		//		UnitView uv = new UnitView(tower, "smackCat.gif"); 
//	}
//
//	@Override
//	public void setGameName(String name) {
//		this.myName = name; 
//	}
//
//	@Override
//	public String getGameName() {
//		return this.myName; 
//	}
//
//	@Override
//	public void setSplashScreen(String fileName) {
//		this.mySplashFileName = fileName;
//	}
//
//	@Override
//	public String getSplashScreen() {
//		return this.mySplashFileName;
//	}
//
//	@Override
//	public List<Level> getLevels() {
//		return this.myLevels;
//	}
//
//	@Override
//	public void addLevel(Level level) {
//		this.myLevels.add(level); 
//	}
//
//	@Override
//	public List<Unit> getPlacedUnits() {
//		return this.myPlacedUnits;
//	}
//
//	@Override
//	public void placeUnit(Unit unit) {
//		this.myPlacedUnits.add(unit); 
//	}
//
//	@Override
//	public List<Unit> getTowers() {
//		return this.myTowers;
//	}
//
//	@Override
//	public List<Unit> getTerrains() {
//		return this.myTerrains;
//	}
//
//	@Override
//	public List<Unit> getEnemies() {
//		return this.myEnemies;
//	}
//
//	@Override
//	public List<Unit> getProjectiles() {
//		return this.myProjectiles;
//	}
//
//	@Override
//	public List<Affector> getAffectors() {
//		return this.myAffectors;
//	}
//
//	@Override
//	public void setPlacedUnits(List<Unit> units) {
//		this.myPlacedUnits = units; 
//	}
//
//	@Override
//	public void setTowers(List<Unit> towers) {
//		this.myTowers = towers; 
//	}
//
//	@Override
//	public void setTerrains(List<Unit> terrains) {
//		this.myTerrains = terrains;
//	}
//
//	@Override
//	public void setEnemies(List<Unit> enemies) {
//		this.myEnemies = enemies; 
//	}
//
//	@Override
//	public void setProjectiles(List<Unit> projectiles) {
//		this.myProjectiles = projectiles; 
//	}
//
//	@Override
//	public void setAffectors(List<Affector> affectors) {
//		this.myAffectors = affectors; 
//	}
//
//	@Override
//	public List<Branch> getEngineBranches() {
//		return this.myEngineBranches;
//	}
//
//	@Override
//	public void setEngineBranches(List<Branch> branches) {
//		if(branches.size() > 0){
//			Branch b = branches.get(0);
//			this.mySpawns.add(new Position(b.getFirstPosition().getX(), b.getFirstPosition().getY()));
//		}
//		this.myEngineBranches = branches; 
//	}
//
//	@Override
//	public List<Position> getGoals() {
//		return this.myGoals;
//	}
//
//	@Override
//	public void setGoals(List<Position> goals) {
//		this.myGoals = goals; 
//	}
//
//	@Override
//	public List<Position> getSpawns() {
//		return this.mySpawns;
//	}
//
//	@Override
//	public void setLevels(List<Level> levels) {
//		this.myLevels = levels; 
//	}
//	
//    @Override
//    public UnitFactory getUnitFactory () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setUnitFactory (UnitFactory factory) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public FunctionFactory getFunctionFactory () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setFunctionFactory (FunctionFactory factory) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public AffectorFactory getAffectorFactory () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setAffectorFactory (AffectorFactory factory) {
//        // TODO Auto-generated method stub
//        
//    }
//	
//}