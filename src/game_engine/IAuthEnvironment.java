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

public interface IAuthEnvironment {
	
	// GlobalGameTab - Brian
	
	public void setGameName(String name);
	
	public String getGameName(); 
	
	public void setSplashScreen(String fileName); 
	
	public String getSplashScreen(); 
	
	// All Levels Tab - Austin
	
	public List<Level> getLevels();
	
	public void addLevel(Level level); 
	
	// These Units have been placed on the Map. Not to be confused with available Enemy and Tower Units.
	
	public void placeUnit(Unit unit); // add this Unit to current List of placed Units 
	
	public void setPlacedUnits(List<Unit> units); 
	
	public List<Unit> getPlacedUnits(); 
	
	// Austin will also use getTowers(), getTerrains(), and getEnemies() (seen below) 
	
	// Create Units + Affector Tab - Virginia
	
	public void setTowers(List<Unit> towers); 
	
	public List<Unit> getTowers(); 
	
	public void setTerrains(List<Unit> terrain); 
	
	public List<Unit> getTerrains(); 
	
	public void setEnemies(List<Unit> enemies); 
	
	public List<Unit> getEnemies(); 
	
	public void setProjectiles(List<Unit> projectiles); 
	
    public List<Unit> getProjectiles();
    
    public void setAffectors(List<Affector> affectors); 
    
    public List<Affector> getAffectors();
    
}
