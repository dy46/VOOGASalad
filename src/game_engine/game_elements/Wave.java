package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;


public class Wave extends GameElement {

    private List<Unit> mySpawningUnits;
    private List<Unit> myPlacingUnits;
    private List<Integer> mySpawnTimes;
    private int myCurrentUnit;
    private int timeSinceLastSpawn;
    private int timeBeforeWave;

    public Wave (String name, int time) {
        super(name);
        timeBeforeWave = time;
        mySpawningUnits = new ArrayList<>();
        myPlacingUnits = new ArrayList<>();
        mySpawnTimes = new ArrayList<>();
        myCurrentUnit = 0;
        timeSinceLastSpawn = 0;
    }

    public Wave (String name, List<Unit> spawning, List<Unit> placing, List<Integer> spawnTimes) {
        super(name);
        mySpawningUnits = spawning;
        myPlacingUnits = placing;
        mySpawnTimes = spawnTimes;
        myCurrentUnit = 0;
        timeSinceLastSpawn = 0;
    }

    public int getTimeBeforeWave () {
        return timeBeforeWave;
    }

    public int getSpawningUnitsLeft () {
        int numUnits = 0;
        for (Unit e : mySpawningUnits) {
            if (e.isVisible()) {
                numUnits++;
            }
        }
        return numUnits;
    }

    public void addSpawningUnit (Unit e, int spawnTime) {
        mySpawningUnits.add(e);
        mySpawnTimes.add(spawnTime);
    }

    public void addPlacingUnit (Unit e, int spawnTime) {
        myPlacingUnits.add(e);
    }

    public List<Unit> getSpawningUnits () {
        return mySpawningUnits;
    }

    public List<Unit> getPlacingUnits () {
        return myPlacingUnits;
    }
    
    public Unit tryToSpawnUnit () {
        timeSinceLastSpawn++;
        if (myCurrentUnit < mySpawningUnits.size() &&
            timeSinceLastSpawn >= mySpawnTimes.get(myCurrentUnit)) {
            Unit unit = mySpawningUnits.get(myCurrentUnit++);
            timeSinceLastSpawn = 0;
            return unit;
        }
        return null;
    }
    
}
