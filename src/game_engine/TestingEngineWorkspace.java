package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import auth_environment.paths.PathGraphFactory;
import auth_environment.paths.PathHandler;
import auth_environment.paths.PathNode;
import exceptions.WompException;
import game_engine.IDFactory;
import game_engine.TestingEngineWorkspace;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TimelineFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.games.GameEngineInterface;
import game_engine.games.Timer;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.physics.CollisionDetector;
import game_engine.physics.EncapsulationDetector;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.store_elements.Store;

public class TestingEngineWorkspace implements GameEngineInterface{

	private int nextWaveTimer;
	private boolean pause;
	private List<Level> myLevels;
	private List<Branch> myBranches;
	private List<Branch> myGridBranches;

	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private CollisionDetector myCollider;
	private EncapsulationDetector myEncapsulator;

	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;
	private int myLives;
	private Store myStore;

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;

	private List<Affector> myAffectors;

	private List<Unit> myTerrains;
	private TerrainFactory myTerrainFactory;

	private List<Position> myGoals;

	private TimelineFactory myTimelineFactory;

	public TestingEngineWorkspace() {};

	public void setUpEngine (Double test) {
		myLives = 3;
		myLevels = new ArrayList<>();
		myBranches = new ArrayList<>();
		myGridBranches = new ArrayList<>();
		//		Branch p2 = new Branch("DirtNew");
		//		p2.addPosition(new Position(0, 30));
		//		p2.addPosition(new Position(200, 30));
		//		p2.addPosition(new Position(200, 200));
		//		p2.addPosition(new Position(400, 200));
		//		p2.addPosition(new Position(400, 525));
		//		myPaths.add(p2);
		myIDFactory = new IDFactory();
		myProjectiles = new ArrayList<>();
		// projectiles must be intialized before towers
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myTimelineFactory = new TimelineFactory(myAffectorFactory.getAffectorLibrary());
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myEnemys = new ArrayList<>();
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTowers = new ArrayList<>();
		myStore = new Store(500);
		makeDummyTowers();
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
		myTerrains = makeDummyTerrains();
		myCollider = new CollisionDetector(this);
		myEncapsulator = new EncapsulationDetector(this);
		myBalance = 0;
		nextWaveTimer = 0;
//		myCurrentLevel = makeDummyLevel();
//		myLevels.add(myCurrentLevel);
//		myGoals = myCurrentLevel.getGoals();
		if(myGoals == null){
			myGoals = new ArrayList<>();
		}
		myAffectorFactory.getAffectorLibrary().getAffectors().stream().forEach(a -> a.setWorkspace(this));  
		this.makeDummyUpgrades();
	}

	private List<Unit> makeDummyTowers () {
		Position position2 = new Position(200, 300);
		Unit t =
				myTowerFactory.createHomingTower("Tower", myProjectiles,
						Collections.unmodifiableList(myTowers),
						position2);
		Unit t2 = 
				myTowerFactory.createTackTower("TackTower", myProjectiles,
						Collections.unmodifiableList(myTowers),
						position2);
		//        myStore.addBuyableTower(t, 100, 1);
		//        myStore.addBuyableTower(t2, 300, 1);
		return new ArrayList<>(Arrays.asList(new Unit[] { t, t2 }));
	}

	private Level makeDummyLevel () {
		//              Wave w = new Wave("I'm not quite sure what goes here", 0);
		//              Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e2 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e3 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e4 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              e1.getProperties().setHealth(50);
		//              e2.getProperties().setHealth(50);
		//              e3.getProperties().setHealth(50);
		//              e4.getProperties().setHealth(50);
		//              w.addEnemy(e1, 0);
		//              w.addEnemy(e2, 60);
		//              w.addEnemy(e3, 60);
		//              w.addEnemy(e4, 60);
		//              Level l = new Level("still not sure", w, 3);
		//              l.addWave(w);
		//              Wave w2 = new Wave("I'm not quite sure what goes here", 240);
		//              Enemy e5 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e6 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e7 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              Enemy e8 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy");
		//              e5.getProperties().setHealth(50);
		//              e6.getProperties().setHealth(50);
		//              e7.getProperties().setHealth(50);
		//              e8.getProperties().setHealth(50);
		//              w2.addEnemy(e5, 0);
		//              w2.addEnemy(e6, 60);
		//              w2.addEnemy(e7, 60);
		//              w2.addEnemy(e8, 60);
		//              Wave w3 = new Wave("I'm not quite sure what goes here", 240);
		//              Enemy e9 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab");
		//              Enemy e10 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab");
		//              Enemy e11 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab");
		//              Enemy e12 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab");
		//              e9.getProperties().setHealth(50);
		//              e10.getProperties().setHealth(50);
		//              e11.getProperties().setHealth(50);
		//              e12.getProperties().setHealth(50);
		//              w3.addEnemy(e9, 0);
		//              w3.addEnemy(e10, 60);
		//              w3.addEnemy(e11, 60);
		//              w3.addEnemy(e12, 60);
		//              l.addWave(w3);
		//              l.addWave(w2);
		//              return l;

		Level l = new Level("Dummy level", 3, this);
		List<Unit> list = makeDummyTowers();
		l.addUnlockedTowerType(list.get(0), 0);
		l.addUnlockedTowerType(list.get(1), 0);

		PathHandler ph = new PathHandler();
		PathGraphFactory pgf = ph.getPGF();
		PathNode grid = ph.getPGF().getPathLibrary().getPathGrid();
		//System.out.println(grid.getBranches());
		myGridBranches.addAll(grid.getBranches());
		
		myBranches.addAll(pgf.getPathLibrary().getBranches());
		List<PathNode> paths = pgf.getPathLibrary().getPaths();
		l.addAllPaths(paths);
		l.addPath(grid);

		// For testing branching
		myBranches.addAll(pgf.getPathLibrary().getBranches());
		//System.out.println("NUM BRANCHES: " + myBranches.size());
//		for(Branch b : myBranches){
//			System.out.println("Branch: " + b.getID()+" Starting Point: " + b.getFirstPosition()+" Ending: "+b.getLastPosition());
//		}
		Branch pb1 = myBranches.get(0);
//		Branch pb2 = myBranches.get(1);
//		Branch pb3 = myBranches.get(2);
//		Branch pb4 = myBranches.get(3);
//		Branch pb5 = myBranches.get(4);
//		Branch pb6 = myBranches.get(5);
//		List<Branch> branches1 = Arrays.asList(pb1, pb6, pb2, pb4);
//		List<Branch> branches2 = Arrays.asList(pb1, pb6, pb3, pb4);
//		PathNode p = new PathNode(0);
//		p.addBranch(pb1);
//		p.addBranch(pb2);
//		p.addBranch(pb3);
//		p.addBranch(pb4);
//		p.addBranch(pb5);
//		p.addBranch(pb6);

//		l.addPath(p);

		Wave w = new Wave("I'm not quite sure what goes here", 0);
		Unit AI1 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit AI2 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit e1 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e2 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e3 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e4 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit AI3 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit AI4 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit rand1 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand2 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand3 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand4 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand5 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand6 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand7 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand8 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand9 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand10 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand11 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit rand12 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		e1.getProperties().setHealth(50);
		e2.getProperties().setHealth(50);
		e3.getProperties().setHealth(50);
		e4.getProperties().setHealth(50);
		AI1.getProperties().setHealth(50);
		AI2.getProperties().setHealth(50);
		AI3.getProperties().setHealth(50);
		AI4.getProperties().setHealth(50);
		rand1.getProperties().setHealth(50);
		rand2.getProperties().setHealth(50);
		rand3.getProperties().setHealth(50);
		rand4.getProperties().setHealth(50);
		rand5.getProperties().setHealth(50);
		rand6.getProperties().setHealth(50);
		rand7.getProperties().setHealth(50);
		rand8.getProperties().setHealth(50);
		rand9.getProperties().setHealth(50);
		rand10.getProperties().setHealth(50);
		rand11.getProperties().setHealth(50);
		rand12.getProperties().setHealth(50);
		w.addEnemy(e1, 60);
		w.addEnemy(e2, 60);
		w.addEnemy(e3, 60);
		w.addEnemy(e4, 60);
		w.addEnemy(AI1, 60);
		w.addEnemy(AI2, 60);
		w.addEnemy(AI3, 60);
		w.addEnemy(AI4, 60);
		w.addEnemy(rand1, 60);
		w.addEnemy(rand2, 60);
		w.addEnemy(rand3, 60);
		w.addEnemy(rand4, 60);
		w.addEnemy(rand5, 60);
		w.addEnemy(rand6, 60);
		w.addEnemy(rand7, 60);
		w.addEnemy(rand8, 60);
		w.addEnemy(rand9, 60);
		w.addEnemy(rand10, 60);
		w.addEnemy(rand11, 60);
		w.addEnemy(rand12, 60);
		l.setMyLives(5);
		l.addWave(w);
		Wave w2 = new Wave("I'm not quite sure what goes here", 240);
		Unit e5 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e6 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e7 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		Unit e8 = myEnemyFactory.createRandomEnemy("Enemy", pb1);
		e5.getProperties().setHealth(50);
		e6.getProperties().setHealth(50);
		e7.getProperties().setHealth(50);
		e8.getProperties().setHealth(50);
		w2.addEnemy(e5, 0);
		w2.addEnemy(e6, 60);
		w2.addEnemy(e7, 60);
		w2.addEnemy(e8, 60);
		Wave w3 = new Wave("I'm not quite sure what goes here", 240);
		Unit e9 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit e10 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit e11 = myEnemyFactory.createAIEnemy("Moab", pb1);
		Unit e12 = myEnemyFactory.createAIEnemy("Moab", pb1);
		e9.getProperties().setHealth(50);
		e10.getProperties().setHealth(50);
		e11.getProperties().setHealth(50);
		e12.getProperties().setHealth(50);
		w3.addEnemy(e9, 60);
		w3.addEnemy(e10, 60);
		w3.addEnemy(e11, 60);
		w3.addEnemy(e12, 60);
		l.addWave(w3);
		l.addWave(w2);
		return l;
	}
	private void makeDummyUpgrades(){
		Affector affector = this.myAffectorFactory.getAffectorLibrary().getAffector("Constant", "HealthDamage");
		affector.setTTL(Integer.MAX_VALUE);
		//		List<Affector> affList = new ArrayList<Affector>();
		//		affList.add(affector);
		//		Affector t = new Affector(affList);
		//		List<AffectorTimeline> init = new ArrayList<AffectorTimeline>();
		//		init.add(t);
		Unit u = new Unit("Interesting", Arrays.asList(affector), 1);
		u.addAffectorToApply(affector);
		myStore.addItem(u, 10);
	}
	private List<Unit> makeDummyTerrains () {
		List<Unit> ice = makeDummyIceTerrain();
		Unit spike = makeDummySpike();
		List<Unit> terrains = new ArrayList<>();
		terrains.addAll(ice);
		terrains.add(spike);
		return terrains;
	}

	private List<Unit> makeDummyIceTerrain () {
		Unit ice1 = myTerrainFactory.getTerrainLibrary().getTerrainByName("IceTerrain");
		List<Position> pos = new ArrayList<>();
		pos.add(new Position(0, 0));
		pos.add(new Position(30, 0));
		pos.add(new Position(30, 30));
		pos.add(new Position(0, 30));
		ice1.getProperties().setPosition(185, 155);
		ice1.getProperties().setBounds(pos);
		ice1.setTTL(Integer.MAX_VALUE);

		Unit ice2 = myTerrainFactory.getTerrainLibrary().getTerrainByName("IceTerrain");
		ice2.getProperties().setPosition(185, 185);
		ice2.getProperties().setBounds(pos);
		ice2.setTTL(Integer.MAX_VALUE);

		Unit ice3 = myTerrainFactory.getTerrainLibrary().getTerrainByName("IceTerrain");
		ice3.getProperties().setPosition(185, 185);
		ice3.getProperties().setBounds(pos);
		ice3.setTTL(Integer.MAX_VALUE);

		Unit ice4 = myTerrainFactory.getTerrainLibrary().getTerrainByName("IceTerrain");
		ice4.getProperties().setPosition(215, 185);
		ice4.getProperties().setBounds(pos);
		ice4.setTTL(Integer.MAX_VALUE);

		return new ArrayList<>(Arrays.asList(new Unit[] { ice1, ice2, ice3, ice4 }));
	}

	//    private Terrain makeDummyPoisonSpike () {
	//        Terrain poisonSpike = myTerrainFactory.getTerrainLibrary().getTerrainByName("PoisonSpikesTerrain");
	//        List<Position> pos = new ArrayList<>();
	//        pos.add(new Position(95, -25));
	//        pos.add(new Position(275, -25));
	//        pos.add(new Position(275, 150));
	//        pos.add(new Position(95, 150));
	//        poisonSpike.getProperties().setPosition(185, 62.5);
	//        poisonSpike.getProperties().setBounds(pos);
	//        poisonSpike.setTTL(Integer.MAX_VALUE);
	//        return poisonSpike;
	//    }

	private Unit makeDummySpike () {
		Unit spike = myTerrainFactory.getTerrainLibrary().getTerrainByName("SpikesTerrain");
		List<Position> pos = new ArrayList<>();
		pos.add(new Position(0, 0));
		pos.add(new Position(0, 30));
		pos.add(new Position(30, 30));
		pos.add(new Position(30, 0));
		spike.getProperties().setPosition(185, 70);
		spike.getProperties().setBounds(pos);
		spike.setTTL(Integer.MAX_VALUE);
		return spike;
	}


	//    public void updateLives () {
	//        int livesToSubtract = 0;
	//        for (int i = 0; i < myEnemys.size(); i++) {
	//            if (myEnemys.get(i).getProperties().getMovement().isUnitAtLastPosition(myEnemys.get(i))) {
	//                livesToSubtract++;
	//                myEnemys.get(i).setElapsedTimeToDeath();
	//            }
	//        }
	//        myCurrentLevel.setMyLives(myCurrentLevel.getStartingLives() - livesToSubtract);
	//    }

	public String getGameStatus () {
		if (myCurrentLevel.getMyLives() <= 0) {
			return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + "0";
		}
		return "Waves remaining: " + myCurrentLevel.wavesLeft() + 
				", Lives remaining: " + myCurrentLevel.getMyLives();
	}

	public void addBalance (double money) {
		myBalance += money;
	}

	public void addLevel (Level level) {
		myLevels.add(level);
	}

	public void remove (Unit unit) {
		String className = unit.getClass().getSimpleName();
		String instanceVarName = "my" + className + "s";
		Field f = null;
		try {
			f = getClass().getDeclaredField(instanceVarName);
		}
		catch (NoSuchFieldException | SecurityException e1) {
			// TODO: womp exception
			e1.printStackTrace();
		}
		f.setAccessible(true);
		List<Unit> listInstanceVar = null;
		try {
			listInstanceVar = (List<Unit>) f.get(this);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: womp exception
			e.printStackTrace();
		}
		listInstanceVar.remove(unit);
	}

	public void modifyTower (int activeTowerIndex, UnitProperties newProperties) {
		// towerBoundsCheck(activeTowerIndex);
		// myTowers.get(activeTowerIndex).setProperties(newProperties);
		//
	}



	// Getters

	public Level getCurrentLevel () {
		return myCurrentLevel;
	}

	public IDFactory getIDFactory () {
		return myIDFactory;
	}

	public double getBalance () {
		return myBalance;
	}

	public List<Level> getLevels () {
		return myLevels;
	}

	public List<Branch> getBranches () {
		return myBranches;
	}

	public List<Unit> getEnemies () {
		return myEnemys;
	}

	public List<Unit> getTowers () {
		return myTowers;
	}

	public List<Unit> getProjectiles () {
		return myProjectiles;
	}

	public FunctionFactory getFunctionFactory () {
		return myFunctionFactory;
	}

	public FunctionLibrary getFunctionLibrary () {
		return myFunctionFactory.getFunctionLibrary();
	}

	public EnemyFactory getEnemyFactory () {
		return myEnemyFactory;
	}

	public AffectorLibrary getAffectorLibrary () {
		return myAffectorFactory.getAffectorLibrary();
	}

	@Override
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
	// change this
	@Override
	public boolean addTower (String name, double x, double y) {
		//        for(int i = 0; i < myStore.getTowerList().size(); i++) {
		//            if(myStore.getTowerList().get(i).toString().equals(name)) {
		//                Unit newTower = myStore.getTowerList().get(i).copyUnit();
		//                newTower.getProperties().setPosition(x, y);
		//                myTowers.add(newTower);
		//            }
		//        }
		Unit purchased = myStore.purchaseUnit(name);
		if(purchased != null){
			Unit copy = purchased.copyUnit();
			copy.getProperties().setPosition(x,y);
			myTowers.add(copy);
			return true;
		}
		return false;
	}

	@Override
	public List<Unit> getTowerTypes () {
		return myStore.getTowerList();
	}

	public List<Affector> getAffectors(){
		return myAffectors;
	}

	@Override
	public void update (){
		myStore.addBuyableTower(myCurrentLevel.getNewUnits());
		nextWaveTimer++;
		boolean gameOver = myLives <= 0;
		if (!pause && !gameOver) {
			myTowers.forEach(t -> t.update());
			myEnemys.forEach(e -> e.update());
			myCollider.resolveEnemyCollisions(myProjectiles);
			myCollider.handleCustomCollisions(this.getAllUnits());
			myEncapsulator.resolveEncapsulations(myTerrains);
			Unit newE = myCurrentLevel.update();
			if (newE != null) {
				myEnemys.add(newE);
			}// tries to spawn new enemies using Waves
			if(myCurrentLevel != null && myCurrentLevel.getCurrentWave() != null){
				if (myCurrentLevel.getCurrentWave().isFinished()) {
					clearProjectiles();
					pause = true;
					nextWaveTimer = 0;
				}
			}
//			myStore.applyItem("Interesting", this.myEnemys);

		}
		else if (myCurrentLevel.getNextWave() != null &&
				myCurrentLevel.getNextWave().getTimeBeforeWave() <= nextWaveTimer) {
			continueWaves();
		}
		myProjectiles.forEach(p -> p.update());
		myProjectiles.removeIf(p -> !p.isVisible());
		myTerrains.forEach(t -> t.update());
		//        updateLives();

	}

	@Override
	public boolean isPaused () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPaused () {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isGameOver () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Timer getTimer () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decrementLives () {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Position> getGoals () {
		return myGoals;
	}

	public List<Unit> getAllUnits(){
		List<Unit> units = new ArrayList<>();
		units.addAll(myTowers);
		units.addAll(myEnemys);
		units.addAll(myProjectiles);
		units.addAll(myTerrains);
		return units;
	}
	
	public List<Branch> getGridBranches(){
		return myGridBranches;
	}

}