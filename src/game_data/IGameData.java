package game_data;

import java.util.List;

import auth_environment.backend.ISettings;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public interface IGameData {
	public void setLevels(List<Level> levels);
	public void addLevel(Level levelToAdd);

	public void setTowerTypes(List<Tower> towerTypes);
	public void addTowerType(Tower towerTypeToAdd);
	
	public void setEnemies(List<Unit> enemies);
	public void setTerrains(List<Unit> terrains);

	public void setProjectiles(List<Unit> projectiles);
	
//	public void setPaths(List<Path> paths);
//	public void addPath(Path pathToAdd);

	public void addPositions(List<Position> list); 

	public List<List<Position>> getPositions();

	public void addGameSettings(ISettings settings);

	//Getters
	public List<Level> getLevels();
	public List<Tower> getTowerTypes();
	public List<Branch> getPaths();
	public ISettings getSettings();
	public List<Unit> getEnemies();
	public List<Unit> getTerrains();
	public List<Unit> getProjectiles();

	public void saveGameData();

}