package auth_environment.Models;

import java.util.List;

import game_engine.IAuthEnvironment;
import game_engine.affectors.Affector;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;

public class SampleAuthData implements IAuthEnvironment {
	
	private String myName;
	private String mySplashFileName;
	private List<Level> myLevels;
	private List<Unit> myTowers;
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors; 
	private List<Unit> myPlacedUnits; 
	
	public SampleAuthData() {
		
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
}
