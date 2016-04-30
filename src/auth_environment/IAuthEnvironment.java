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
	
	public List<Position> getGoals();
	
	public void setGoals(List<Position> goals);
	
	public List<Position> getSpawns();
	
	public void setSpawns(List<Position> spawns); 
	
	// Unit / Affector Creator Tab
	
	public List<Unit> getTowers(); 

	public void setTowers(List<Unit> towers); 
	
	public List<Unit> getTerrains(); 

	public void setTerrains(List<Unit> terrains); 
		
	public List<Unit> getEnemies(); 

	public void setEnemies(List<Unit> enemies); 	
	
    public List<Unit> getProjectiles();

	public void setProjectiles(List<Unit> projectiles); 
	    	
	// For UnitCreation integration, issue 190
	
	public FunctionFactory getFunctionFactory();
	
	public void setFunctionFactory(FunctionFactory factory); 
	
	// IGameData Methods
	
	public List<Level> getLevels();
    public void setLevels(List<Level> levels);

    public List<Branch> getBranches();
    public void setBranches(List<Branch> branches);
    
    public List<Unit> getPlacedUnits();
    public void setPlacedUnits(List<Unit> units);

    public List<Affector> getAffectors();
	public AffectorFactory getAffectorFactory();
	public void setAffectorFactory(AffectorFactory factory); 

    public List<PlaceValidation> getPlaceValidations();
    public void setPlaceValidations(List<PlaceValidation> placeValidations);
    
    public WaveGoal getWaveGoal();
    public void setWaveGoal(WaveGoal waveGoal);
    
    public ScoreUpdate getScoreUpdate();
    public void setScoreUpdate(ScoreUpdate scoreUpdate);

    public Store getStore();
    public void setStore(Store store);
    
    public double getScore();
    public void setScore(double score);
	
	public UnitFactory getUnitFactory();
	public void setUnitFactory(UnitFactory factory); 
	
	public MapHandler getMapHandler();
	public void setMapHandler(MapHandler mapHandler);
}