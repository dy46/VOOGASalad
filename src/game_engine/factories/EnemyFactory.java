package game_engine.factories;

import game_engine.affectors.AffectorLibrary;
import game_engine.game_elements.Enemy;

public class EnemyFactory {

	private AffectorLibrary myAffectorLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}

	public Enemy createConstantEnemy(String name){
		return new Enemy(name, myAffectorLibrary.getAffector("Constant", "PositionMove"));
	}
	
	public Enemy createExpIncrEnemy(String name){
		return new Enemy(name, myAffectorLibrary.getAffector("ExpIncr", "PositionMove"));
	}
	
	public Enemy createExpDecrEnemy(String name){
		return new Enemy(name, myAffectorLibrary.getAffector("ExpDecr", "PositionMove"));
	}

}