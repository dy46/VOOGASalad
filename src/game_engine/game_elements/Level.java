package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;

import auth_environment.paths.PathNode;
import game_engine.games.Timer;
import game_engine.properties.Position;


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

    public Level (String name, int myLives) {
        super(name);
        // setID(getWorkspace().getIDFactory().createID(this));
        myWaves = new ArrayList<>();
        this.myLives = myLives;
        this.startingLives = myLives;
        myWaveTimer = new Timer();
        myPaths = new ArrayList<>();
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

    public Wave getCurrentWave () {
        return myCurrentWave;
    }

    public void setCurrentWave (int wave) {
        checkCurrentWaveFinished();
        myCurrentWave = myWaves.get(wave);
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
        return myCurrentWave.tryToSpawnEnemy();
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

	public List<PathNode> getPaths() {
		return myPaths;
	}

	public void decrementLife() {
		myLives--;
	}
	
	public List<Wave> getWaves(){
		return myWaves;
	}

	public List<Position> getGoals() {
		List<Position> goals = new ArrayList<>();
		for(PathNode p : myPaths){
			List<Branch> branches = p.getBranches();
			for(Branch b : branches){
				Position lastPos = b.getLastPosition();
				List<Branch> forwardNeighbors = b.getForwardNeighbors();
				if(forwardNeighbors.size() == 0)
					goals.add(lastPos);
			}
		}
		return goals;
	}

}