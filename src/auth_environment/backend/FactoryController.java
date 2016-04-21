package auth_environment.backend;

import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TowerFactory;

public class FactoryController {

	private FunctionFactory myFunctionFactory;
	private AffectorFactory myAffectorFactory;
	private EnemyFactory myEnemyFactory;
	private TowerFactory myTowerFactory;
	private TerrainFactory myTerrainFactory;
	
	public FactoryController(){
		myFunctionFactory = new FunctionFactory();
		myAffectorFactory = new AffectorFactory(myFunctionFactory);
		myEnemyFactory = new EnemyFactory(myAffectorFactory.getAffectorLibrary());
		myTowerFactory = new TowerFactory(myAffectorFactory.getAffectorLibrary());
		myTerrainFactory = new TerrainFactory(myAffectorFactory.getAffectorLibrary());
	}
	
	public FunctionFactory getFunctionFactory(){
		return myFunctionFactory;
	}

	public TerrainFactory getTerrainFactory(){
		return myTerrainFactory;
	}

	public AffectorFactory getAffectorFactory(){
		return myAffectorFactory;
	}

	public EnemyFactory getEnemyFactory(){
		return myEnemyFactory;
	}

	public TowerFactory getTowerFactory(){
		return myTowerFactory;
	}
	
}