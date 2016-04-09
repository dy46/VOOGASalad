package game_engine.factories;

import java.util.Collections;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorLibrary;
import game_engine.game_elements.Enemy;

public class EnemyFactory {

	private AffectorLibrary myAffectorLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}

	public Enemy createConstantEnemy(String name){
	    return createSpecifiedEnemy(name, "Constant", "PositionMove");
	}
	
	public Enemy createExpIncrEnemy(String name){
	    return createSpecifiedEnemy(name, "ExpInc", "PositionMove");
	}
	
	public Enemy createExpDecrEnemy(String name){
	        return createSpecifiedEnemy(name, "ExpDecr", "PositionMove");
	}
	
	public Enemy createSpecifiedEnemy(String name, String behavior, String property) {
	    Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
            moveAffector.setTTL(Integer.MAX_VALUE);
            return new Enemy(name, Collections.singletonList(moveAffector));
	}
	

}