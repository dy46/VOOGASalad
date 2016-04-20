package auth_environment.Models;

import java.util.List;

import game_engine.IAuthInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;

public class SampleAuthData implements IAuthInterface {
	
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
	public void addTower(Unit tower) {
		this.myTowers.add(tower); 
	}

	@Override
	public List<Unit> getTowers() {
		return this.myTowers;
	}

	@Override
	public void clearTowers() {
		this.myTowers.clear();
	}

	@Override
	public void addTerrain(Unit terrain) {
		this.myTerrains.add(terrain);
	}

	@Override
	public List<Unit> getTerrains() {
		return this.getTerrains();
	}

	@Override
	public void clearTerrains() {
		this.myTerrains.clear();
	}

	@Override
	public void addEnemy(Unit enemy) {
		this.myEnemies.add(enemy);
	}

	@Override
	public List<Unit> getEnemies() {
		return this.myEnemies;
	}

	@Override
	public void clearEnemies() {
		this.myEnemies.clear();
	}

	@Override
	public void addProjectile(Unit projectile) {
		this.myProjectiles.add(projectile); 
	}

	@Override
	public List<Unit> getProjectiles() {
		return this.myProjectiles;
	}

	@Override
	public void clearProjectiles() {
		this.myProjectiles.clear();
	}

	@Override
	public void addAffector(Affector affector) {
		this.myAffectors.add(affector);
	}

	@Override
	public List<Affector> getAffectors() {
		return this.myAffectors;
	}

	@Override
	public void clearAffectors() {
		this.myAffectors.clear();
	}

}
