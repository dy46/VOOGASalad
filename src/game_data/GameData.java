package game_data;

import java.util.ArrayList;
import java.util.List;

import auth_environment.backend.GameSettings;
import auth_environment.backend.ISettings;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class GameData implements IGameData {
	private List<Level> myLevels = new ArrayList<Level>();
	private List<Tower> myTowerTypes = new ArrayList<Tower>();
	private List<Path> myPaths = new ArrayList<Path>();
	private ISettings mySettings = new GameSettings();
//	private List<Unit> myTerrains;
	private IDataConverter<IPlayerEngineInterface> mySerializer = new AuthSerializer<IPlayerEngineInterface>();
	private List<List<Position>> myPositionLists;
	
	@Override
	public void setLevels(List<Level> levels) {
		myLevels = levels;
	}
	@Override
	public void addLevel(Level levelToAdd) {
		myLevels.add(levelToAdd);
	}
	
	@Override
	public void setTowerTypes(List<Tower> towerTypes) {
		myTowerTypes = towerTypes;
	}
	@Override
	public void addTowerType(Tower towerTypeToAdd) {
		myTowerTypes.add(towerTypeToAdd);
	}

//	@Override
//	public void setPaths(List<Path> paths) {
//		myPaths = paths;
//	}
//	@Override
//	public void addPath(Path pathToAdd) {
//		myPaths.add(pathToAdd);
//	}
	@Override
	public void addGameSettings(ISettings settings) {
		mySettings = settings;
	}

//	@Override
//	public void setTerrains(List<Unit> terrains) {
//		myTerrains = terrains;
//	}

	// Getters
	@Override
	public List<Level> getLevels() {
		return myLevels;
	}
	@Override
	public List<Tower> getTowerTypes() {
		return myTowerTypes;
	}
	@Override
	public List<Path> getPaths() {
		return myPaths;
	}
	@Override
	public ISettings getSettings() {
		return mySettings;
	}
	
	
	@Override
	public void saveGameData() {
		IPlayerEngineInterface workspace = new EngineWorkspace();
	    workspace.setPaths(myPaths);
	    workspace.setTowerTypes (myTowerTypes);
	    workspace.setLevels (myLevels);
		mySerializer.saveElement(workspace);
	}
	@Override
	public void addPositions(List<Position> list) {
		myPositionLists.add(list);
	}
	
	@Override
	public List<List<Position>> getPositions() {
		return myPositionLists;
	}
	
}