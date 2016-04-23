package game_data;

import java.util.List;

import auth_environment.IAuthEnvironment;
import game_engine.affectors.Affector;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;

public class GameData {
	
	private String myName;
	private String mySplashFileName;
	private List<Level> myLevels;
	private List<Unit> myTowers;
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors; 
	private List<Unit> myPlacedUnits; 
	
	public GameData() {
		
	}

	
	public void setGameName(String name) {
		this.myName = name; 
	}

	
	public String getGameName() {
		return this.myName; 
	}

	
	public void setSplashScreen(String fileName) {
		this.mySplashFileName = fileName;
	}

	
	public String getSplashScreen() {
		return this.mySplashFileName;
	}

	
	public List<Level> getLevels() {
		return this.myLevels;
	}

	
	public void addLevel(Level level) {
		this.myLevels.add(level); 
	}

	
	public List<Unit> getPlacedUnits() {
		return this.myPlacedUnits;
	}

	
	public void placeUnit(Unit unit) {
		this.myPlacedUnits.add(unit); 
	}

	
	public List<Unit> getTowers() {
		return this.myTowers;
	}

	
	public List<Unit> getTerrains() {
		return this.myTerrains;
	}

	
	public List<Unit> getEnemies() {
		return this.myEnemies;
	}

	
	public List<Unit> getProjectiles() {
		return this.myProjectiles;
	}

	
	public List<Affector> getAffectors() {
		return this.myAffectors;
	}

	
	public void setPlacedUnits(List<Unit> units) {
		this.myPlacedUnits = units; 
	}

	
	public void setTowers(List<Unit> towers) {
		this.myTowers = towers; 
	}

	
	public void setTerrains(List<Unit> terrains) {
		this.myTerrains = terrains;
	}

	
	public void setEnemies(List<Unit> enemies) {
		this.myEnemies = enemies; 
	}

	
	public void setProjectiles(List<Unit> projectiles) {
		this.myProjectiles = projectiles; 
	}

	
	public void setAffectors(List<Affector> affectors) {
		this.myAffectors = affectors; 
	}
}
