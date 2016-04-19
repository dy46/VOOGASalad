package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import auth_environment.paths.PathNode;
import game_data.GameData;
import game_engine.CollisionDetector;
import game_engine.IDFactory;
import game_engine.TestingEngineWorkspace;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.games.GameEngineInterface;
import game_engine.games.Timer;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.store_elements.Store;

public class TestingEngineWorkspace implements GameEngineInterface{

	private int nextWaveTimer;
	private boolean pause;
	private List<Level> myLevels;
	private List<Branch> myBranches;

	private List<Unit> myTowers;
	private List<Unit> myEnemys;
	private List<Unit> myProjectiles;

	private CollisionDetector myCollider;
	private Store myStore;
	private Level myCurrentLevel;
	private IDFactory myIDFactory;
	private double myBalance;
	private int myLives;

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;

//	private TimelineFactory myTimelineFactory;

	private List<Affector> myAffectors;

	private List<Unit> myTerrains;
	private TerrainFactory myTerrainFactory;

	private List<Position> myGoals;

	public TestingEngineWorkspace() {};

	public void setUpEngine (GameData gameData) {
		myLives = 3;
		myLevels = new ArrayList<>();
		myBranches = new ArrayList<>();
		Branch p2 = new Branch("DirtNew");
		p2.addPosition(new Position(0, 30));
		p2.addPosition(new Position(200, 30));
		p2.addPosition(new Position(200, 200));
		p2.addPosition(new Position(400, 200));
		p2.addPosition(new Position(400, 525));
		myBranches.add(p2);
		myIDFactory = new IDFactory();
		myProjectiles = new ArrayList<>();
		// projectiles must be intialized before towers
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
//		myTimelineFactory = new TimelineFactory(myAffectorFactory.getAffectorLibrary());
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myEnemys = new ArrayList<>();
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTowers = new ArrayList<>();
		myStore = new Store(500);
		makeDummyTowers();
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
		myTerrains = makeDummyTerrains();
		myCollider = new CollisionDetector(this);
		myBalance = 0;
		nextWaveTimer = 0;
		myCurrentLevel = makeDummyLevel();
		myLevels.add(myCurrentLevel);
		myGoals = myCurrentLevel.getGoals();
		myAffectorFactory.getAffectorLibrary().getAffectors().stream().forEach(a -> a.setWorkspace(this)); 
		
	}

	private void makeDummyTowers () {
		Position position2 = new Position(200, 300);
		Tower t =
				myTowerFactory.createHomingTower("Tower", myProjectiles,
						Collections.unmodifiableList(myTowers),
						position2);
		Tower t2 = 
				myTowerFactory.createTackTower("Tack", myProjectiles,
						Collections.unmodifiableList(myTowers),
						position2);
		Tower t3 = myTowerFactory.createRespawningTower("Tower", myProjectiles,
				Collections.unmodifiableList(myTowers),
				position2);
		myStore.addBuyableTower(t, 100);
		myStore.addBuyableTower(t2, 200);
		myStore.addBuyableTower(t3, 400);
		
		
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

		Level l = new Level("Dummy level", 3);

		Branch b1 = new Branch("DirtNew");
		b1.addPosition(new Position(0, 30));
		b1.addPosition(new Position(200, 30));

		Branch b2 = new Branch("DirtNew");
		b2.addPosition(new Position(200, 30));
		b2.addPosition(new Position(400, 30));
		b2.addPosition(new Position(400, 200));

		Branch b3 = new Branch("DirtNew");
		b3.addPosition(new Position(200, 30));
		b3.addPosition(new Position(200, 200));
		b3.addPosition(new Position(400, 200));

		Branch b4 = new Branch("DirtNew");
		b4.addPosition(new Position(400, 200));
		b4.addPosition(new Position(400, 525));

		b1.addNeighbor(b2);
		b1.addNeighbor(b3);
		b2.addNeighbor(b1);
		b2.addNeighbor(b3);
		b2.addNeighbor(b4);
		b3.addNeighbor(b1);
		b3.addNeighbor(b2);
		b3.addNeighbor(b4);
		b3.addNeighbor(b1);
		b4.addNeighbor(b2);
		b4.addNeighbor(b3);

		List<Branch> branches1 = Arrays.asList(b1, b2, b4);
		List<Branch> branches2 = Arrays.asList(b1, b3, b4);

		PathNode p = new PathNode(0);
		p.addBranch(b1);
		p.addBranch(b2);
		p.addBranch(b3);
		p.addBranch(b4);
		l.addPath(p);

		Wave w = new Wave("I'm not quite sure what goes here", 0);
		Enemy AI1 = myEnemyFactory.createAIEnemy("Moab", b1);
		Enemy AI2 = myEnemyFactory.createAIEnemy("Moab", b1);
		Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e2 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy e3 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e4 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy AI3 = myEnemyFactory.createAIEnemy("Moab", b1);
		Enemy AI4 = myEnemyFactory.createAIEnemy("Moab", b1);
		Enemy random1 = myEnemyFactory.createRandomEnemy("Enemy", b1);
		Enemy random2 = myEnemyFactory.createRandomEnemy("Enemy", b1);
		Enemy random3 = myEnemyFactory.createRandomEnemy("Enemy", b1);
		Enemy random4 = myEnemyFactory.createRandomEnemy("Enemy", b1);
		Enemy random5 = myEnemyFactory.createRandomEnemy("Enemy", b1);
		e1.getProperties().setHealth(50);
		e2.getProperties().setHealth(50);
		e3.getProperties().setHealth(50);
		e4.getProperties().setHealth(50);
		AI1.getProperties().setHealth(50);
		AI2.getProperties().setHealth(50);
		AI3.getProperties().setHealth(50);
		AI4.getProperties().setHealth(50);
		random1.getProperties().setHealth(50);
		random2.getProperties().setHealth(50);
		random3.getProperties().setHealth(50);
		random4.getProperties().setHealth(50);
		random5.getProperties().setHealth(50);
		w.addEnemy(e1, 0);
		w.addEnemy(e2, 60);
		w.addEnemy(e3, 60);
		w.addEnemy(e4, 60);
		w.addEnemy(AI1, 60);
		w.addEnemy(AI2, 60);
		w.addEnemy(AI3, 60);
		w.addEnemy(AI4, 60);
		w.addEnemy(random1, 60);
		w.addEnemy(random2, 60);
		w.addEnemy(random3, 60);
		w.addEnemy(random4, 60);
		w.addEnemy(random5, 60);
		l.setMyLives(5);
		l.addWave(w);
		Wave w2 = new Wave("I'm not quite sure what goes here", 240);
		Enemy e5 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e6 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy e7 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e8 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		e5.getProperties().setHealth(50);
		e6.getProperties().setHealth(50);
		e7.getProperties().setHealth(50);
		e8.getProperties().setHealth(50);
		w2.addEnemy(e5, 0);
		w2.addEnemy(e6, 60);
		w2.addEnemy(e7, 60);
		w2.addEnemy(e8, 60);
		Wave w3 = new Wave("I'm not quite sure what goes here", 240);
		Enemy e9 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e10 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy e11 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e12 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		e9.getProperties().setHealth(50);
		e10.getProperties().setHealth(50);
		e11.getProperties().setHealth(50);
		e12.getProperties().setHealth(50);
		w3.addEnemy(e9, 0);
		w3.addEnemy(e10, 60);
		w3.addEnemy(e11, 60);
		w3.addEnemy(e12, 60);
		l.addWave(w3);
		l.addWave(w2);
		return l;
	}

	private List<Unit> makeDummyTerrains () {
		List<Terrain> ice = makeDummyIceTerrain();
		Terrain spike = makeDummySpike();
		List<Unit> terrains = new ArrayList<>();
		terrains.addAll(ice);
		terrains.add(spike);
		return terrains;
	}
	
	private List<Terrain> makeDummyIceTerrain () {
		Terrain ice1 = myTerrainFactory.getTerrainLibrary().getTerrainByName("Ice");
		List<Position> pos = new ArrayList<>();
		pos.add(new Position(0, 0));
		pos.add(new Position(30, 0));
		pos.add(new Position(30, 30));
		pos.add(new Position(0, 30));
		ice1.getProperties().setPosition(185, 155);
		ice1.getProperties().setBounds(pos);
		ice1.setTTL(Integer.MAX_VALUE);

		Terrain ice2 = myTerrainFactory.getTerrainLibrary().getTerrainByName("Ice");
		ice2.getProperties().setPosition(185, 185);
		ice2.getProperties().setBounds(pos);
		ice2.setTTL(Integer.MAX_VALUE);

		Terrain ice3 = myTerrainFactory.getTerrainLibrary().getTerrainByName("Ice");
		ice3.getProperties().setPosition(185, 185);
		ice3.getProperties().setBounds(pos);
		ice3.setTTL(Integer.MAX_VALUE);

		Terrain ice4 = myTerrainFactory.getTerrainLibrary().getTerrainByName("Ice");
		ice4.getProperties().setPosition(215, 185);
		ice4.getProperties().setBounds(pos);
		ice4.setTTL(Integer.MAX_VALUE);

		return new ArrayList<>(Arrays.asList(new Terrain[] { ice1, ice2, ice3, ice4 }));
	}

	//    private Terrain makeDummyPoisonSpike () {
	//        Terrain poisonSpike = myTerrainFactory.getTerrainLibrary().getTerrainByName("PoisonSpikes");
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

	private Terrain makeDummySpike () {
		Terrain spike = myTerrainFactory.getTerrainLibrary().getTerrainByName("Spikes");
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

	public void addEnemy (Enemy enemy) {
		myEnemys.add(enemy);
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
		// myTowerTypes.set(activeTowerIndex, newProperties);
		//
	}

	private void towerBoundsCheck (int index) {
		if (index < 0 || index > myTowers.size()) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void towerTypeBoundsCheck (int index) {
		if (index < 0 || index > myStore.getUnitListSize()) {
			throw new IndexOutOfBoundsException();
		}
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

	public List<Branch> getPaths () {
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

	@Override
	public void addTower (String name, double x, double y) {
//		for(int i = 0; i < myStore.getUnitListSize(); i++) {
//			if(myTowerTypes.get(i).toString().equals(name)) {
//				Tower newTower = myTowerTypes.get(i).copyTower(x, y);
//				myTowers.add(newTower);
//			}
//		}
		Tower u = (Tower)myStore.purchaseUnit(name);
		if(u != null){
			myTowers.add(u.copyTower(x, y));
		}
	}

	@Override
	public List<Unit> getTowerTypes () {
		return myStore.getTowerList();
	}

	public List<Affector> getAffectors(){
		return myAffectors;
	}

	@Override
	public void update () {
		nextWaveTimer++;
		boolean gameOver = myLives <= 0;
		if (!pause && !gameOver) {
			myTowers.forEach(t -> t.update());
			myTowers.forEach(t -> ((Tower) t).fire());
			myEnemys.forEach(e -> e.update());
			myCollider.resolveEnemyCollisions(myProjectiles, myTerrains);
			Enemy newE = myCurrentLevel.update();
			if (newE != null) {
				myEnemys.add(newE);
			}// tries to spawn new enemies using Waves
			if (myCurrentLevel.getCurrentWave().isFinished()) {
				clearProjectiles();
				pause = true;
				nextWaveTimer = 0;
			}
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

	public boolean isGameOver(){
		return myCurrentLevel.getMyLives() <= 0;
	}

	@Override
	public Timer getTimer () {
		// TODO Auto-generated method stub
		return null;
	}

	public void decrementLives(){
		myCurrentLevel.decrementLife();
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

}
