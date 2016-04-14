package game_data;

import java.util.List;

import auth_environment.backend.GameSettings;
import auth_environment.backend.ISettings;
import game_engine.game_elements.Level;
import game_engine.game_elements.Tower;
import game_engine.properties.Position;

public class GameData implements IGameData {
	
	private ISettings mySettings;
	
	public GameData() {
		this.mySettings = new GameSettings(); 
	}

	@Override
	public List<Tower> getTowers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Level getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTowers(List<Tower> towers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLevel(Level level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettings getSettings() {
		return this.mySettings;
	}

	@Override
	public void updateSettings(ISettings settings) {
		this.mySettings = settings; 
	}

	@Override
	public void addPositions(List<Position> list) {
		
	}

}
