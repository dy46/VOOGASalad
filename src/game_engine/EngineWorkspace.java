package game_engine;

import java.util.ArrayList;
import java.util.List;
import game_engine.AI.AIHandler;
import game_engine.AI.AISearcher;
import game_engine.AI.AISimulator;
import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.physics.CollisionDetector;
import game_engine.physics.EncapsulationDetector;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

public class EngineWorkspace implements GameEngineInterface {

    private int nextWaveTimer;
    private List<Branch> myBranches;
    private List<Affector> myAffectors;
    private LevelController myLevelController;
    private CollisionDetector myCollider;
    private EncapsulationDetector myEncapsulator;
    private UnitController myUnitController;
    private WaveGoal waveGoal;
    private ScoreUpdate scoreUpdate;
    private List<Unit> unitsToRemove;
    private Position cursorPos;
    private AIHandler myAIHandler;
    private AISimulator myAISimulator;
	private AISearcher myAISearcher;

    public void setUpEngine (TestingGameData data) {
        unitsToRemove = new ArrayList<>();
        myAISimulator = new AISimulator(this);
		myAISearcher = new AISearcher(this);
        myAIHandler = new AIHandler(this);
        waveGoal = data.getWaveGoal();
        scoreUpdate = data.getScoreUpdate();
        myBranches = data.getBranches();
        myAffectors = data.getAffectors();
        myAffectors.stream().forEach(a -> a.setWorkspace(this));
        myCollider = new CollisionDetector(this);
        myEncapsulator = new EncapsulationDetector(this);
        myLevelController =
                new LevelController(data.getLevels(), data.getScore(), data.getPaused());
        List<PlaceValidation> myPlaceValidations = data.getPlaceValidations();
        myPlaceValidations.stream().forEach(pv -> pv.setEngine(this));
        myUnitController =
                new UnitController(data.getPlacedUnits(), myPlaceValidations,
                                   data.getStore(), unitsToRemove);
        updateAIBranches();
    }

    @Override
    public void update () {
        Level myCurrentLevel = myLevelController.getCurrentLevel();
        Store myStore = myUnitController.getStore();
        List<Unit> placingUnits = myCurrentLevel.getCurrentWave().getPlacingUnits();
        myUnitController.getStore().clearBuyableUnits();
        // TODO: store should not be updated here
        placingUnits.stream().forEach(u -> myStore.addBuyableUnit(u, 100));
        nextWaveTimer++;
        waveProgression(myCurrentLevel);
        myUnitController.getUnitType("Projectile").forEach(p -> p.update());
        myUnitController.getUnitType("Projectile").removeIf(p -> !p.isVisible());
        myUnitController.getUnitType("Terrain").forEach(t -> t.update());
        scoreUpdate.updateScore(this, myCurrentLevel);
        unitsToRemove.stream().forEach(r -> r.setInvisible());
        unitsToRemove.clear();
    }

    public void waveProgression (Level myCurrentLevel) {
        if (!myLevelController.isPaused() && !myLevelController.isGameOver()) {
            myUnitController.getUnitType("Tower").forEach(t -> t.update());
            myUnitController.getUnitType("Enemy").forEach(e -> e.update());
            myCollider.resolveEnemyCollisions(myUnitController.getUnitType("Projectile"));
            myEncapsulator.resolveEncapsulations(myUnitController.getUnitType("Terrain"));
            Unit newE = myCurrentLevel.update();
            if (newE != null) {
                myUnitController.getPlacedUnits().add(newE);
            }
        }
        if (myCurrentLevel.getNextWave() != null && waveGoal.reachedGoal(this)) {
            nextWaveTimer = 0;
            myLevelController.continueWaves();
        }
        if (myUnitController.getUnitType("Enemy").size() == 0) {
            myUnitController.clearProjectiles();
        }
    }

    public List<String> saveGame () {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Affector> getAffectors () {
        return myAffectors;
    }

    @Override
    public List<Branch> getBranches () {
        return myBranches;
    }

    @Override
    public int getNextWaveTimer () {
        return nextWaveTimer;
    }

    @Override
    public void setCursorPosition (double x, double y) {
        cursorPos = new Position(x, y);
    }

    public Position getCursorPosition () {
        return cursorPos;
    }

    @Override
    public List<Branch> getBranchesAtPos (Position pos) {
        return myAIHandler.getBranchesAtPos(pos);
    }

    @Override
    public void updateAIBranches () {
        myAIHandler.updateAIBranches();
    }

    @Override
    public UnitController getUnitController () {
        return myUnitController;
    }

    @Override
    public LevelController getLevelController () {
        return myLevelController;
    }
    
    @Override
	public AIHandler getAIHandler() {
		return myAIHandler;
	}
	
	@Override
	public AISearcher getAISearcher() {
		return myAISearcher;
	}
	
	public AISimulator getAISimulator(){
		return myAISimulator;
	}

}