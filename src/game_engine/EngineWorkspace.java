package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import auth_environment.IAuthEnvironment;
import game_engine.AI.AIHandler;
import game_engine.affectors.Affector;
import game_engine.factories.FunctionFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.games.Timer;
import game_engine.physics.CollisionDetector;
import game_engine.physics.EncapsulationController;
import game_engine.place_validations.EnemySpawnPointPlaceValidation;
import game_engine.place_validations.PlaceValidation;
import game_engine.properties.Position;
import game_engine.score_updates.EnemyDeathScoreUpdate;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.EnemyNumberWaveGoal;
import game_engine.wave_goals.WaveGoal;

public class EngineWorkspace implements GameEngineInterface{

	private int nextWaveTimer;
	private List<Branch> myBranches; 
	private List<Level> myLevels;
	private List<Unit> myTowers;
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors;
	private List<Unit> myPlacedUnits;
	private Level myCurrentLevel;
	private boolean pause;
	private CollisionDetector myCollider;
	private EncapsulationController myEncapsulator;
	private Store myStore;
	private double score;
	private Timer myTimer;
	private FunctionFactory myFunctionFactory;
	private WaveGoal waveGoal;
	private ScoreUpdate scoreUpdate;
	private List<PlaceValidation> myPlaceValidations;
	private List<Unit> unitsToRemove;
	private Position cursorPos;
	private AIHandler myAIHandler;

	public void setUpEngine (IAuthEnvironment data) {
		myAIHandler = new AIHandler(this);
		myPlaceValidations = new ArrayList<>();
		myPlaceValidations.add(new EnemySpawnPointPlaceValidation(this));
		unitsToRemove = new ArrayList<>();
		waveGoal = new EnemyNumberWaveGoal();
		scoreUpdate = new EnemyDeathScoreUpdate();
		myBranches = data.getEngineBranches();
		myLevels = data.getLevels();
		myTowers = data.getTowers();
		myEnemies = data.getEnemies();
		myTerrains = data.getTerrains();
		myProjectiles = data.getProjectiles();
		myAffectors = data.getAffectors();
		myPlacedUnits = data.getPlacedUnits();
		if(myLevels.size() > 0){
			myCurrentLevel = myLevels.get(0);
			myCurrentLevel.setSpawns(data.getSpawns());
			myCurrentLevel.setGoals(data.getGoals());
		}
		initialize();
	}

	private void initialize(){
		myFunctionFactory = new FunctionFactory();
		myTimer = new Timer();
		myCollider = new CollisionDetector(this);
		myEncapsulator = new EncapsulationController(this);
		if(myBranches == null)	this.myBranches = new ArrayList<Branch>();
		if(myLevels == null)	this.myLevels = new ArrayList<Level>();
		if(myTowers == null)	this.myTowers = new ArrayList<Unit>();
		if(myEnemies == null)	this.myEnemies = new ArrayList<Unit>();
		if(myTerrains == null)	this.myTerrains = new ArrayList<Unit>();
		if(myProjectiles == null)	this.myProjectiles = new ArrayList<Unit>();
		if(myAffectors == null)	this.myAffectors = new ArrayList<Affector>();
		if(myPlacedUnits == null)	this.myPlacedUnits = new ArrayList<Unit>();
		if(myStore == null)		myStore = new Store(500);
		if(myLevels.size() > 0){
			myCurrentLevel = myLevels.get(0);
			myCurrentLevel.setGoals(new ArrayList<>());
			myCurrentLevel.setSpawns(new ArrayList<>());
		}
	}

	public String getGameStatus () {
		if (myCurrentLevel.getMyLives() <= 0) {
			return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + "0";
		}
		return "Waves remaining: " + myCurrentLevel.wavesLeft() + 
				", Lives remaining: " + myCurrentLevel.getMyLives();
	}

//	public void addBalance (double money) {
//		myBalance += money;
//	}
	
	public int getMoney() {
		return myStore.getMoney();
	}
	public void addLevel (Level level) {
		myLevels.add(level);
	}

	public int getLives () {
		return myCurrentLevel.getMyLives();
	}

	public void clearProjectiles () {
		myProjectiles.forEach(t -> {
			t.setInvisible();
			t.setHasCollided(true);
		});
	}

	public List<Unit> getTerrains () {
		return myTerrains;
	}

	public List<String> saveGame () {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel (int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = false;
	}

	public void playWave (int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves () {
		myCurrentLevel.playNextWave();
		pause = false;
	}

	public boolean addTower (String name, double x, double y) {
		Unit purchased = myStore.purchaseUnit(name);
		if (purchased != null) {
			boolean canPlace = false;
			for(int i = 0; i < myPlaceValidations.size(); i++) {
				canPlace = myPlaceValidations.get(i).validate(purchased, x, y);
			}
			if(canPlace) {
				Unit copy = purchased.copyUnit();
				copy.getProperties().setPosition(x, y);
				myTowers.add(copy);
				return true;
			}
			else {
				myStore.sellUnit(purchased);
			}
		}
		return false;
	}

	public List<Unit> getTowerTypes () {
		return myStore.getTowerList();
	}

	public List<Affector> getAffectors(){
		return myAffectors;
	}

	@Override
	public void update () {
		List<Unit> placingUnits = myCurrentLevel.getCurrentWave().getPlacingUnits();
		myStore.clearBuyableUnits();
		placingUnits.stream().forEach(u -> myStore.addBuyableUnit(u, 100));
		nextWaveTimer++;
		boolean gameOver = myCurrentLevel.getMyLives() <= 0;
		if (!pause && !gameOver) {
			myTowers.forEach(t -> t.update());
			myEnemies.forEach(e -> e.update());
			myCollider.resolveEnemyCollisions(myProjectiles);
			myEncapsulator.resolveEncapsulations(myTerrains);
			Unit newE = myCurrentLevel.update();
			if (newE != null) {
				myEnemies.add(newE);
			}
		}
		if (myCurrentLevel.getNextWave() != null && waveGoal.reachedGoal(this)) {
			nextWaveTimer = 0;
			System.out.println("NEXT WAVE");
			continueWaves();
		}
		if (myEnemies.size() == 0) {
			clearProjectiles();
		}
		myProjectiles.forEach(p -> p.update());
		myProjectiles.removeIf(p -> !p.isVisible());
		myTerrains.forEach(t -> t.update());
		scoreUpdate.updateScore(this, myCurrentLevel);
		unitsToRemove.stream().forEach(r -> r.setInvisible());
		unitsToRemove.clear();
	}

	public void decrementLives () {
		myCurrentLevel.decrementLife();
	}

	public List<Unit> getAllUnits(){
		List<Unit> units = new ArrayList<>();
		units.addAll(myTowers);
		units.addAll(myEnemies);
		units.addAll(myProjectiles);
		units.addAll(myTerrains);
		return units;
	}

	public List<Unit> getEnemies() {
		return myEnemies;
	}

	@Override
	public List<Unit> getTowers() {
		return myTowers;
	}

	@Override
	public List<Level> getLevels() {
		return myLevels;
	}

	@Override
	public List<Unit> getProjectiles() {
		return myProjectiles;
	}

	@Override
	public List<Branch> getBranches() {
		return myBranches;
	}

	@Override
	public boolean isPaused() {
		return myTimer.isPaused();
	}

	@Override
	public void setPaused() {
		myTimer.pause();
	}

	@Override
	public boolean isGameOver() {
		return myLevels.get(myLevels.size()-1).wavesLeft() == 0;
	}

	@Override
	public Timer getTimer() {
		return myTimer;
	}

	@Override
	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	@Override
	public FunctionFactory getFunctionFactory() {
		return myFunctionFactory;
	}

	@Override
	public double getScore () {
		return score;
	}

	public void setScore (double score) {
		this.score = score;
	}

	@Override
	public int getNextWaveTimer () {
		return nextWaveTimer;
	}

	public void decrementLives (int lives) {
		myCurrentLevel.decrementLives(lives);
	}

	@Override
	public void sellUnit(Unit u) {
		List<String> namesOfChildren = new ArrayList<>();
		u.getChildren().stream().forEach(c -> namesOfChildren.add(c.toString()));
		unitsToRemove.addAll(getAllUnits().stream().filter(c -> namesOfChildren.contains(c.toString()))
				.collect(Collectors.toList()));
		u.setInvisible();
		u.update();
		unitsToRemove.add(u);
		myStore.sellUnit(u);
	}

	public List<Affector> getUpgrades(Unit unitToUpgrade) {
		return myStore.getUpgrades(unitToUpgrade);
	}

	public void applyUpgrade(Unit unitToUpgrade, Affector affector) {
		myStore.buyUpgrade(unitToUpgrade, affector);
	}

	@Override
	public void moveUnit (Unit unit, double x, double y) {
		unit.getProperties().setPosition(new Position(x, y));
	}

	@Override
	public void setCursorPosition (double x, double y) {
		cursorPos = new Position(x, y);      
	}

	public Position getCursorPosition() {
		return cursorPos;
	}

	public void removeTower(Unit u) {
		if(myTowers.contains(u)){
			myTowers.remove(u);
		}
	}
	
	@Override
	public List<Branch> getBranchesAtPos(Position pos) {
		return myAIHandler.getBranchesAtPos(pos);
	}

	@Override
	public void updateAIBranches() {
		myAIHandler.updateAIBranches();
	}

}