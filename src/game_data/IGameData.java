package game_data;

import java.util.List;

import auth_environment.IAuthEnvironment;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

// TODO: make this also extend the Game Player's interface

public interface IGameData extends IAuthEnvironment {
	
	public void setLevels(List<Level> levels);
	public void addLevel(Level levelToAdd);
	public void setEnemies(List<Unit> enemies);
	public void setTerrains(List<Unit> terrains);
	public void setProjectiles(List<Unit> projectiles);
	public void addPositions(List<Position> list); 

	public List<List<Position>> getPositions();

	//Getters
	public List<Level> getLevels();
	public List<Unit> getTowerTypes();
	public List<Branch> getPaths();
	public List<Unit> getEnemies();
	public List<Unit> getTerrains();
	public List<Unit> getProjectiles();
	
}