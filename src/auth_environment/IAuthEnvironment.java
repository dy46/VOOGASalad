package auth_environment;

import java.util.List;

import auth_environment.paths.MapHandler;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;
import game_engine.affectors.Affector;

import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.UnitFactory;

import game_data.IGameData;


public interface IAuthEnvironment extends IGameData {
	
	// GlobalGameTab
	
	public String getGameName(); 
	public void setGameName(String name);
		
	public String getSplashScreen(); 
	public void setSplashScreen(String fileName); 	
	
	// Path Tab
	
	public List<Unit> getTowers(); 
	public void setTowers(List<Unit> towers); 
	
	public List<Unit> getTerrains(); 
	public void setTerrains(List<Unit> terrains); 
		
	public List<Unit> getEnemies(); 
	public void setEnemies(List<Unit> enemies); 	
	
    public List<Unit> getProjectiles();
	public void setProjectiles(List<Unit> projectiles); 
	
	public List<Position> getGoals();
	public void setGoals(List<Position> goals);

	public List<Position> getSpawns();
	public void setSpawns(List<Position> spawns);
	    	
	// For UnitCreation integration, issue 190
	
	public FunctionFactory getFunctionFactory();
	
	public void setFunctionFactory(FunctionFactory factory); 
	
}