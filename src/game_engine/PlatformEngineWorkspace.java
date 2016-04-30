package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import auth_environment.paths.MapHandler;
import game_engine.AI.AIHandler;
import game_engine.AI.AISearcher;
import game_engine.AI.AISimulator;
import game_engine.affectors.Affector;
import game_engine.controllers.EnemyController;
import game_engine.controllers.LevelController;
import game_engine.controllers.UnitController;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.interfaces.ILevelDisplayer;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.physics.CollisionDetector;
import game_engine.physics.EncapsulationDetector;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.score_updates.EnemyDeathScoreUpdate;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Pair;
import game_engine.store_elements.Store;
import game_engine.wave_goals.EnemyNumberWaveGoal;
import game_engine.wave_goals.WaveGoal;

public class PlatformEngineWorkspace implements GameEngineInterface {

	private int nextWaveTimer;
	private boolean pause;
	private List<Level> myLevels;
	private List<Branch> myBranches;
	private List<Unit> unitsToRemove;
	private Position cursorPos;

	private WaveGoal waveGoal;
	private ScoreUpdate scoreUpdate;

	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private CollisionDetector myCollider;
	private EncapsulationDetector myEncapsulator;

	private Level myCurrentLevel;
	private double myBalance;
	private Store myStore;
	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;

	private List<Affector> myAffectors;

	private List<Unit> myTerrains;
	private TerrainFactory myTerrainFactory;

	private AIHandler myAIHandler;
	private AISimulator myAISimulator;
	private AISearcher myAISearcher;

	public PlatformEngineWorkspace() {
	};

	public void setUpEngine(TestingGameData test) {
		myAISimulator = new AISimulator(this);
		myAISearcher = new AISearcher(this);
		myAIHandler = new AIHandler(this);
		unitsToRemove = new ArrayList<>();
		new ArrayList<>();
		// myPlaceValidations.add(new TowerPlaceValidation(this));
		waveGoal = new EnemyNumberWaveGoal();
		scoreUpdate = new EnemyDeathScoreUpdate();
		myLevels = new ArrayList<>();
		myBranches = new ArrayList<>();
		myProjectiles = new ArrayList<>();
		// projectiles must be intialized before towers
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myEnemys = new ArrayList<>();
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTowers = new ArrayList<>();
		myStore = new Store(500000);
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
		myTerrains = makeDummyTerrains();
		myCollider = new CollisionDetector(this);
		myEncapsulator = new EncapsulationDetector(this);
		myBalance = 0;
		nextWaveTimer = 0;
		myCurrentLevel = makeDummyLevel();
		myLevels.add(myCurrentLevel);
		// updateAIBranches();
		myAffectorFactory.getAffectorLibrary().getAffectors().stream().forEach(a -> a.setWorkspace(this));
		this.myAffectors = myAffectorFactory.getAffectorLibrary().getAffectors();
		this.makeDummyUpgrades();
	}

	private List<Unit> makeTowers(Wave w) {
		Position position2 = new Position(200, 300);
		Unit t = myTowerFactory.createHomingTower("Cannon_Tower", myProjectiles, Collections.unmodifiableList(myTowers),
				position2, myStore);
		
		/*Unit t = myTowerFactory.createHomingTower("Tower", myProjectiles, Collections.unmodifiableList(myTowers),
				position2, myStore);*/
		Pair<Unit, Integer> towerPair = new Pair<Unit, Integer>(t, 100);
		List<Pair<Unit, Integer>> towers = new ArrayList<Pair<Unit, Integer>>();

		towers.add(towerPair);

		for (Pair<Unit, Integer> p : towers)
			w.addPlacingUnit((Unit) p.getLeft());

		myStore.addBuyableUnit(towers);
		return new ArrayList<>(Arrays.asList(new Unit[] { t }));
	}

	private Level makeDummyLevel() {

		Level l = new Level("Platformer Tower Defense", 20);
		MapHandler mh = new MapHandler(new ArrayList<Branch>(), new ArrayList<Branch>(), new ArrayList<Branch>());

		List<Position> path = new ArrayList<>();

		path.add(new Position(500, 200));
		path.add(new Position(200, 200));
		path.add(new Position(150, 400));
		path.add(new Position(50, 400));
		path.add(new Position(500, 401));

		mh.processPositions(path);
		mh.addSpawn(path.get(0));
		mh.addGoal(path.get(path.size() - 1));

		myBranches = mh.getEngineBranches();
		l.setGoals(mh.getGoals());
		l.setSpawns(mh.getSpawns());
		Wave w = new Wave("I'm not quite sure what goes here", 0);

		for (int i = 0; i < 100; i++) {
			Unit u = myEnemyFactory.createAIEnemy("GooEnemy", l.getSpawns().get(0));
			u.getProperties().setHealth(100);

			w.addSpawningUnit(u, i * 30);
		}

		List<Unit> list = makeTowers(w);
		l.addWave(w);

		// Affector affector =
		// this.myAffectorFactory.getAffectorLibrary().getAffector("Constant",
		// "HealthDamage");
		// myStore.addUpgrade(list.get(1), affector, 100);
		Affector affector2 = this.myAffectorFactory.getAffectorLibrary().getAffector("Constant", "HealthDamage");
		myStore.addUpgrade(list.get(0), affector2, 100);
		return l;
	}

	private void makeDummyUpgrades() {
		Affector affector = this.myAffectorFactory.getAffectorLibrary().getAffector("Constant", "HealthDamage");
		affector.setTTL(Integer.MAX_VALUE);
		// List<Affector> affList = new ArrayList<Affector>();
		// affList.add(affector);
		// Affector t = new Affector(affList);
		// List<AffectorTimeline> init = new ArrayList<AffectorTimeline>();
		// init.add(t);
		Unit u = new Unit("Interesting", Arrays.asList(affector), 1);
		u.addAffectorToApply(affector);
		// myStore.addItem(u, 10);
	}

	private List<Unit> makeDummyTerrains() {
		return makeTerrain();
	}

	private List<Unit> makeTerrain() {

		List<Unit> terrain = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Unit groundTerrain = myTerrainFactory.getTerrainLibrary().getTerrainByName("GroundTerrain");
			List<Position> pos = new ArrayList<>();
			pos.add(new Position(0, 0));
			pos.add(new Position(60, 0));
			pos.add(new Position(60, 60));
			pos.add(new Position(0, 60));
			groundTerrain.getProperties().setBounds(pos);
			groundTerrain.getProperties().setPosition(10, i * 10);
			groundTerrain.setTTL(Integer.MAX_VALUE);
			terrain.add(groundTerrain);
		}

		return terrain;
	}

	public String getGameStatus() {
		if (myCurrentLevel.getMyLives() <= 0) {
			return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + "0";
		}
		return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + myCurrentLevel.getMyLives();
	}

	public void addBalance(double money) {
		myBalance += money;
	}

	public void addLevel(Level level) {
		myLevels.add(level);
	}

	public void remove(Unit unit) {
		String className = unit.getClass().getSimpleName();
		String instanceVarName = "my" + className + "s";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		List<Unit> listInstanceVar = null;
		try {
			listInstanceVar = (List<Unit>) f.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		listInstanceVar.remove(unit);
	}

	public void modifyTower(int activeTowerIndex, UnitProperties newProperties) {
		// towerBoundsCheck(activeTowerIndex);
		// myTowers.get(activeTowerIndex).setProperties(newProperties);
		//
	}

	// Getters

	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	public double getBalance() {
		return myBalance;
	}

	public List<Level> getLevels() {
		return myLevels;
	}

	public List<Branch> getBranches() {
		return myBranches;
	}

	public List<Unit> getEnemies() {
		return myEnemys;
	}

	public List<Unit> getTowers() {
		return myTowers;
	}

	public List<Unit> getProjectiles() {
		return myProjectiles;
	}

	public FunctionFactory getFunctionFactory() {
		return myFunctionFactory;
	}

	public FunctionLibrary getFunctionLibrary() {
		return myFunctionFactory.getFunctionLibrary();
	}

	public EnemyFactory getEnemyFactory() {
		return myEnemyFactory;
	}

	public AffectorLibrary getAffectorLibrary() {
		return myAffectorFactory.getAffectorLibrary();
	}

	public void clearProjectiles() {
		myProjectiles.forEach(t -> {
			t.setInvisible();
			t.setHasCollided(true);
		});
	}

	public List<Unit> getTerrains() {
		return myTerrains;
	}

	public List<String> saveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel(int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = false;
	}

	public void playWave(int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves() {
		myCurrentLevel.playNextWave();
		pause = false;
	}

	public List<Affector> getAffectors() {
		return myAffectors;
	}

	@Override
	public void update() {
		List<Unit> placingUnits = myCurrentLevel.getCurrentWave().getPlacingUnits();
		myStore.clearBuyableUnits();
		placingUnits.stream().forEach(u -> myStore.addBuyableUnit(u, 100));
		nextWaveTimer++;
		boolean gameOver = myCurrentLevel.getMyLives() <= 0;
		if (!pause && !gameOver) {
			myTowers.forEach(t -> t.update());
			myEnemys.forEach(e -> e.update());
			myCollider.resolveEnemyCollisions(myProjectiles);
			myEncapsulator.resolveEncapsulations(myTerrains);
			Unit newE = myCurrentLevel.update();
			if (newE != null) {
				myEnemys.add(newE);
			} // tries to spawn new enemies using Waves
				// myStore.applyItem("Interesting", this.myEnemys);
		}
		if (myCurrentLevel.getNextWave() != null && waveGoal.reachedGoal(this)) {
			nextWaveTimer = 0;
			continueWaves();
		}
		if (myEnemys.size() == 0) {
			clearProjectiles();
		}
		myProjectiles.forEach(p -> p.update());
		myProjectiles.removeIf(p -> !p.isVisible());
		myTerrains.forEach(t -> t.update());
		scoreUpdate.updateScore(this, myCurrentLevel);
		unitsToRemove.stream().forEach(r -> r.setInvisible());
		unitsToRemove.clear();
		// updateLives();

	}

	public List<Unit> getAllUnits() {
		List<Unit> units = new ArrayList<>();
		units.addAll(myTowers);
		units.addAll(myEnemys);
		units.addAll(myProjectiles);
		units.addAll(myTerrains);
		return units;
	}

	public void setScore(double score) {
	}

	@Override
	public int getNextWaveTimer() {
		return nextWaveTimer;
	}

	public List<Affector> getUpgrades(Unit unitToUpgrade) {
		return myStore.getUpgrades(unitToUpgrade);
	}

	public void applyUpgrade(Unit unitToUpgrade, Affector affector) {
		myStore.buyUpgrade(unitToUpgrade, affector);
	}

	@Override
	public void setCursorPosition(double x, double y) {
		cursorPos = new Position(x, y);
	}

	public Position getCursorPosition() {
		return cursorPos;
	}

	public void removeTower(Unit u) {
		if (myTowers.contains(u)) {
			myTowers.remove(u);
		}
	}

	@Override
	public List<Branch> getBranchesAtPos(Position pos) {
		return myAIHandler.getBranchesAtPos(pos);
	}

	public int getMoney() {
		return myStore.getMoney();
	}

	public void updateAIBranches() {
		myAIHandler.updateAIBranches();
	}

	@Override
	public UnitController getUnitController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LevelController getLevelController() {
		// TODO Auto-generated method stub
		return null;
	}

	public Store getStore() {
		return myStore;
	}

	@Override
	public AIHandler getAIHandler() {
		return myAIHandler;
	}

	@Override
	public AISearcher getAISearcher() {
		return myAISearcher;
	}

	@Override
	public AISimulator getAISimulator() {
		return myAISimulator;
	}

	@Override
	public ILevelDisplayer getLevelDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnemyController getEnemyController() {
		// TODO Auto-generated method stub
		return null;
	}

}