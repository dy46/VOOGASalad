package game_engine.games;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import auth_environment.paths.PathNode;
import game_data.GameData;
import game_engine.CollisionDetector;
import game_engine.IDFactory;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TimelineFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.game_elements.Wave;
import game_engine.genres.TD.TDGame;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;


/**
 * This class represents the "workspace" for a single instance of a game.
 * This workspace holds game instance specific data.
 * 
 *
 */

public class TestTDGame extends TDGame {

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;
	private TimelineFactory myTimelineFactory;
	private TerrainFactory myTerrainFactory;

	public TestTDGame(){
		setUpEngine(null);
	}

	public void setUpEngine (GameData gameData) {
		super.setupTimer(this);
		super.nullCheck();
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myTimelineFactory = new TimelineFactory(myAffectorFactory.getAffectorLibrary());
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary(), myTimelineFactory.getTimelineLibrary());
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
		super.setTerrains(makeDummyTerrains());
		super.setTowerTypes(makeDummyTowers());
		super.setCurrentLevel(makeDummyLevel());
		super.addLevel(getCurrentLevel());
		super.setGoals();
		setupAffectorWorkspaces();
	}

	private List<Tower> makeDummyTowers () {
		Position position2 = new Position(200, 300);
		Tower t =
				myTowerFactory.createHomingTower("Tower", getProjectiles(),
						Collections.unmodifiableList(getTowers()),
						position2);
		Tower t2 = 
				myTowerFactory.createTackTower("Tack", getProjectiles(),
						Collections.unmodifiableList(getTowers()),
						position2);
		return new ArrayList<>(Arrays.asList(new Tower[] { t, t2 }));
	}

	private Level makeDummyLevel () {
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
		Enemy AI1 = myEnemyFactory.createAIEnemy("Enemy", b1);
		Enemy AI2 = myEnemyFactory.createAIEnemy("Enemy", b1);
		Enemy e1 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e2 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy e3 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches1);
		Enemy e4 = myEnemyFactory.createPathFollowPositionMoveEnemy("Enemy", branches2);
		Enemy AI3 = myEnemyFactory.createAIEnemy("Enemy", b1);
		Enemy AI4 = myEnemyFactory.createAIEnemy("Enemy", b1);
		e1.getProperties().setHealth(50);
		e2.getProperties().setHealth(50);
		e3.getProperties().setHealth(50);
		e4.getProperties().setHealth(50);
		AI1.getProperties().setHealth(50);
		AI2.getProperties().setHealth(50);
		AI3.getProperties().setHealth(50);
		AI4.getProperties().setHealth(50);
//		w.addEnemy(e1, 0);
//		w.addEnemy(e2, 60);
//		w.addEnemy(e3, 60);
//		w.addEnemy(e4, 60);
		w.addEnemy(AI1, 0);
		w.addEnemy(AI2, 60);
		w.addEnemy(AI3, 60);
		w.addEnemy(AI4, 60);
		
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
//		w2.addEnemy(e6, 60);
//		w2.addEnemy(e7, 60);
//		w2.addEnemy(e8, 60);
		Wave w3 = new Wave("I'm not quite sure what goes here", 240);
		Enemy e9 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab", branches1);
		Enemy e10 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab", branches2);
		Enemy e11 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab", branches1);
		Enemy e12 = myEnemyFactory.createPathFollowPositionMoveEnemy("Moab", branches2);
		e9.getProperties().setHealth(50);
		e10.getProperties().setHealth(50);
		e11.getProperties().setHealth(50);
		e12.getProperties().setHealth(50);
		w3.addEnemy(e9, 0);
//		w3.addEnemy(e10, 60);
//		w3.addEnemy(e11, 60);
//		w3.addEnemy(e12, 60);
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
		spike.getProperties().setPosition(15, 15);
		spike.getProperties().setBounds(pos);
		spike.setTTL(Integer.MAX_VALUE);
		return spike;
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

}