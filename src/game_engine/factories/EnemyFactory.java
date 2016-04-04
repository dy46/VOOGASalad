package game_engine.factories;

import game_engine.affectors.AffectorLibrary;
import game_engine.game_elements.Enemy;

public class EnemyFactory {

	private AffectorLibrary myAffectorLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}

	public Enemy createEnemy(String name){
		return new Enemy(name, myAffectorLibrary.getAffector("Constant", "PositionMove"));
	}

}