package auth_environment.backend;

import java.util.List;

import game_engine.game_elements.Level;
import game_engine.game_elements.Tower;

public interface GameTypeData {
	
	public List<Tower> getTowers();
	
	public Level getLevel(); 

}
