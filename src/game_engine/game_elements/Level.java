package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

import auth_environment.paths.PathNode;
import game_engine.EngineWorkspace;
import game_engine.GameEngineInterface;
import game_engine.games.Timer;
import game_engine.properties.Position;
import game_engine.store_elements.Pair;


/*
 * Internal API that is used in order to represent levels within a game. More specifically,
 * this API will be responsible for dealing with transitions in between waves of enemies, as well as
 * keeping track of the order in which waves occur, and the initial conditions for waves.
 */
public class Level extends GameElement {

	private int myLives;
	private int startingLives;
	private Wave myCurrentWave;
	private List<Wave> myWaves;
	private Timer myWaveTimer;
	private List<PathNode> myPaths;
	private GameEngineInterface myWorkspace;
	private List<Pair<Unit, Integer>> unlockedTowerTypes;
	private List<Position> myGoals;
	private List<Position> mySpawns;

	public Level (String name, int myLives, GameEngineInterface workspace) {
		super(name);
		// setID(getWorkspace().getIDFactory().createID(this));
		myWaves = new ArrayList<>();
		this.myLives = myLives;
		this.startingLives = myLives;
		myWaveTimer = new Timer();
		myPaths = new ArrayList<>();
		this.myWorkspace = workspace;
		this.unlockedTowerTypes = new ArrayList<Pair<Unit, Integer>>();
	}

	/*
	 * This API will allow the player to start a new wave. Returns true if next started, false if
	 * not.
	 */
	public void playNextWave () {
		if (!myCurrentWave.isFinished()) {
			return; // TODO: should probably fix this to tell player that you cannot skip this wave
		}
		checkCurrentWaveFinished();
		Wave nextWave = getNextWave();
		if (nextWave != null) {
			myCurrentWave = nextWave;
		}
	}
	public List<Pair<Unit, Integer>> getNewUnits(){
		return unlockedTowerTypes;
	}

	public Wave getCurrentWave () {
		return myCurrentWave;
	}

	public void setCurrentWave (int wave) {
		checkCurrentWaveFinished();
		myCurrentWave = myWaves.get(wave);
	}
	public void addUnlockedTowerType(Unit u, int cost){
		this.unlockedTowerTypes.add(new Pair<Unit, Integer>(u, cost));
	}
	private void checkCurrentWaveFinished () {
		if (!myCurrentWave.isFinished()) {
			// TODO: Throw exception "Current wave not finished"
		}
	}

	/*
	 * Returns boolean value on whether or not the player has completed the current wave
	 */
	public boolean waveFinished () {
		return (myWaves.size() == 0) ||
				(myCurrentWave.isFinished() && myWaves.get(myWaves.size() - 1) == myCurrentWave);
	}

	public int wavesLeft () {
		int numWavesLeft = myWaves.size();
		for (Wave wave : myWaves) {
			if (wave == myCurrentWave) {
				break;
			}
			numWavesLeft--;
		}
		return numWavesLeft;
	}

	/*
	 * Allows the level to set new waves for the level
	 */
	public void addWave (Wave newWave) {
		myWaves.add(newWave);
		if(myWaves.size () == 1){
			myCurrentWave = newWave;
		}
	}

	public Unit update () {
		if(myCurrentWave == null)
			return null;
		if(!(myGoals == null)){
			for(Position goal : myGoals){
				for(Unit enemy : myCurrentWave.getEnemiesLeft()){
					if(enemy.getProperties().getPosition().equals(goal)){
						System.out.println("Killing enemy at goal.");
						enemy.kill();
					}
				}
			}
		}
		return myCurrentWave.update();
	}

	public Wave getNextWave () {
		for (int x = 0; x < myWaves.size() - 1; x++) {
			if (myWaves.get(x).equals(myCurrentWave)) {
				int nextWaveIndex = x + 1;
				return myWaves.get(nextWaveIndex);
			}
		}
		return null;
	}

	public String toString () {
		return String.valueOf(myWaves.size());
	}

	public int getMyLives () {
		return myLives;
	}

	public void setMyLives (int myLives) {
		this.myLives = myLives;
	}

	public int getStartingLives () {
		return startingLives;
	}

	public Timer getWaveTimer() {
		return myWaveTimer;
	}

	public void addPath(PathNode path) {
		myPaths.add(path);
	}

	public void addAllPaths(List<PathNode> paths) {
		myPaths.addAll(paths);
	}

	public List<PathNode> getPaths() {
		return myPaths;
	}

	public void decrementLife() {
		myLives--;
	}

	public List<Wave> getWaves(){
		return myWaves;
	}

	public void setGoals(List<Position> goals){
		this.myGoals = goals;
	}

	public void setSpawns(List<Position> spawns){
		this.mySpawns = spawns;
	}

	public void addSpawn(Position spawn){
		this.mySpawns.add(spawn);
	}

	public void addGoal(Position goal){
		this.myGoals.add(goal);
	}

	public List<Position> getGoals(){
		return myGoals;
	}

	public void addGoals(List<Position> goals){
		this.myGoals.addAll(goals);
	}

	public void addSpawns(List<Position> spawns){
		this.mySpawns.addAll(spawns);
	}

	public List<Position> getSpawns(){
		return mySpawns;
	}

	public void addWaves(List<Wave> waves) {
		this.myWaves.addAll(waves);
	}
	
	public boolean isGoal(Position goal){
		if(myGoals == null)
			return false;
		return myGoals.contains(goal);
	}

}