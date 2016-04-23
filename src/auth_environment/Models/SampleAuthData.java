package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.IAuthEnvironment;
import game_engine.TestingEngineWorkspace;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

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
	private List<Branch> myGridBranches;
	private List<Branch> myPathBranches; 
	private List<Level> myLevels;
	private List<Unit> myTowers; 
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors; // Will eventually be replaced with a Library
	private List<Unit> myPlacedUnits; 
	private List<Position> mySpawns;
	private List<Position> myGoals; 

	public SampleAuthData() {
		this.myName = "sampleGame";
		this.mySplashFileName = "smackCat.gif";
		this.myGridBranches = new ArrayList<Branch>();
		this.myPathBranches = new ArrayList<Branch>();
		this.myLevels = new ArrayList<Level>();
		this.myTowers = new ArrayList<Unit>();
		this.myEnemies = new ArrayList<Unit>();
		this.myTerrains = new ArrayList<Unit>();
		this.myProjectiles = new ArrayList<Unit>();
		this.myAffectors = new ArrayList<Affector>(); 
		this.myPlacedUnits = new ArrayList<Unit>(); 
		this.mySpawns = new ArrayList<Position>();
		this.myGoals = new ArrayList<Position>(); 
		this.setupDummyValues();
	}

	private void setupDummyValues() {
		TestingEngineWorkspace test = new TestingEngineWorkspace();
		test.setUpEngine(null);
		this.setTerrains(test.getTerrains());
		this.setTowers(test.getTowers());
		this.setLevels(test.getLevels());
		this.setEnemies(test.getEnemies());
		this.setProjectiles(test.getProjectiles());
		this.setAffectors(test.getAffectors());
		this.myGoals.add(new Position(450, 450));
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
	public List<Branch> getPathBranches() {
		return this.myPathBranches;
	}

	@Override
	public void setPathBranches(List<Branch> branches) {
		Branch b = branches.get(0);
		this.mySpawns.add(new Position(b.getFirstPosition().getX(), b.getFirstPosition().getY()));
		this.myPathBranches = branches; 
	}

	@Override
	public List<Branch> getGridBranches() {
		return this.myGridBranches;
	}

	@Override
	public void setGridBranches(List<Branch> branches) {
		this.myGridBranches = branches; 
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
}
