package game_data;

import java.util.List;

import auth_environment.backend.ISettings;
import game_engine.game_elements.Level;
import game_engine.game_elements.Tower;
import game_engine.properties.Position;

/**
 * Created by BrianLin on 4/12/16.
 * 
 * Team member responsible: Austin
 * 
 * This class handles Game Data at the highest level (nothing beyond this class will every be written to XML).
 */

public interface IGameData {
	
	public ISettings getSettings(); 
	
	public void updateSettings(ISettings settings); 
	
	public List<Tower> getTowers();
	
	public Level getLevel(); 
	
	public void setTowers(List<Tower> towers);
	
	public void addLevel(Level level); 
	
	public void addPositions(List<Position> list); 
	
}
