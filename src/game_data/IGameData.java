package game_data;

import java.util.List;

import auth_environment.backend.ISettings;
import game_engine.game_elements.Level;
import game_engine.game_elements.Tower;

public interface IGameData {
	
	public ISettings getSettings(); 
	
	public List<Tower> getTowers();
	
	public Level getLevel(); 
	
	public void setTowers(List<Tower> towers);
	
	public void addTower(Tower tower);
	
	public void addLevel(Level level); 
	
}
