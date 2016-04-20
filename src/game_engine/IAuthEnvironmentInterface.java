package game_engine;

import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;

/**
 * This interface is the external API for the Auth Environment (excluding Factory calls). 
 * @author Brian Lin
 *
 */

public interface IAuthEnvironmentInterface {
	
	// GlobalGameTab - Brian
	
	public void setGameName(String name);
	
	public String getGameName(); 
	
	public void setSplashScreen(String fileName); 
	
	public String getSplashScreen(); 
	
	// All Levels Tab - Austin
	
	public List<Level> getLevels();
	
	public void addLevel(Level level); 
	
	// These Units have been placed on the Map. Not to be confused with available Enemy and Tower Units. 
	public List<Unit> getPlacedUnits(); 

	public void placeUnit(Unit unit); // add this Unit to current List of placed Units 
	
	// Austin will also use getTowers(), getTerrains(), and getEnemies() (seen below) 
	
	// Create Units + Affector Tab - Virginia
	
	public void addTower(Unit tower);
	
	public List<Unit> getTowers(); 
	
	public void clearTowers(); 
	
	public void addTerrain(Unit terrain);
	
	public List<Unit> getTerrains(); 
	
	public void clearTerrains(); 
	
	public void addEnemy(Unit enemy);
	
	public List<Unit> getEnemies(); 
	
	public void clearEnemies();
	
	public void addProjectile(Unit projectile); 
	
    public List<Unit> getProjectiles();
    
    public void clearProjectiles(); 
    
    public void addAffector(Affector affector); 
    
    public List<Affector> getAffectors();
    
    public void clearAffectors(); 
    
}
