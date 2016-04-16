package game_data;

import java.util.ArrayList;
import java.util.List;

import auth_environment.backend.GameSettings;
import auth_environment.backend.ISettings;
import game_engine.affectors.Affector;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.games.GameEngineInterface;
import game_engine.genres.TD.TDGame;
import game_engine.properties.Position;

public class GameData implements IGameData {
	private List<Level> myLevels;
	private List<Tower> myTowerTypes;
	private List<Unit> myTowers;
	private List<Branch> myPaths;
	private List<Unit> myTerrains;
	private List<Unit> myEnemies;
	private List<Unit> myProjectiles;
	private List<Affector> myAffectors;
	private ISettings mySettings;
	//	private List<Unit> myTerrains;
	private IDataConverter<GameEngineInterface> mySerializer;
	private List<List<Position>> myPositionLists;

	public GameData(){
		myAffectors = new ArrayList<>();
		myLevels = new ArrayList<>();
		myTowerTypes = new ArrayList<>();
		myPaths = new ArrayList<>();
		myTerrains = new ArrayList<>();
		myProjectiles = new ArrayList<>();
		mySettings = new GameSettings();
		mySerializer = new AuthSerializer<GameEngineInterface>();
	}

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
	public List<Branch> getPaths() {
		return myPaths;
	}
	@Override
	public ISettings getSettings() {
		return mySettings;
	}

	@Override
	public void saveGameData() {
		GameEngineInterface workspace = new TDGame();
		workspace.setUpEngine(this);
		mySerializer.saveElement(workspace);
	}

	public void addPositions(List<Position> list) {
		myPositionLists.add(list);
	}

	public List<List<Position>> getPositions() {
		return myPositionLists;
	}
	
	public void setEnemies(List<Unit> enemies) {
		this.myEnemies = enemies;
	}
	
	public void setTerrains(List<Unit> terrains) {
		this.myTerrains = terrains;
	}
	
	public List<Unit> getEnemies() {
		return myEnemies;
	}
	
	public List<Unit> getTerrains() {
		return myTerrains;
	}

	public List<Unit> getTowers() {
		return myTowers;
	}
	
	public void setTowers(List<Unit> towers){
		this.myTowers = towers;
	}

	public List<Unit> getProjectiles() {
		return myProjectiles;
	}
	
	public void setProjectiles(List<Unit> projectiles){
		this.myProjectiles = projectiles;
	}

	public void setPaths(List<Branch> paths) {
		this.myPaths = paths;
	}
	
	public void setAffectors(List<Affector> affectors){
		this.myAffectors = affectors;
	}

	public List<Affector> getAffectors() {
		return myAffectors;
	}

}