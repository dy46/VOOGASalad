package game_engine.physics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;
import game_engine.properties.Position;


public class CollisionDetector {

	private GameEngineInterface myEngine;

	public CollisionDetector (GameEngineInterface engine) {
		myEngine = engine;
	}

	public void resolveEnemyCollisions (List<Unit> myProjectiles) {
		myEngine.getEnemies().forEach(t -> updateEnemies(t, myProjectiles));
	}

	// returns which Unit from the list collided with the target unit
	private void updateEnemies (Unit unit, List<Unit> myProjectiles) {
		for (int i = 0; i < myProjectiles.size(); i++) {
			if (!(unit == myProjectiles.get(i)) && CollisionChecker.collides(unit, myProjectiles.get(i))) {
				if (!myProjectiles.get(i).hasCollided() && unit.isVisible()) {
					List<Affector> affectorsToApply = myProjectiles.get(i).getAffectorsToApply()
							.stream().map(a -> a.copyAffector()).collect(Collectors.toList());                     
					unit.addAffectors(affectorsToApply);
//					unit.addAffector(myAffectorFactory.getAffectorLibrary().getAffector("Velocity", "Stop"));
					myProjectiles.get(i).setHasCollided(true);        
					myProjectiles.get(i).setElapsedTimeToDeath();
				}
			}
		}
	}	
	
//	public static void main(String[] args){
//		CollisionDetector test = new CollisionDetector(null);
//		Unit u1 = new Unit("1", null);
//		u1.setType("Type1");
//		System.out.println(u1.getAffectors().size());
//		Unit u2 = new Unit("2", null);
//		u2.setType("Type2");
//		List<Unit> testList = new ArrayList<Unit>();
//		testList.add(u1);
//		testList.add(u2);
//		test.handleCustomCollisions(testList);
//		System.out.println(u1.getAffectors().size());
//		System.out.println(u1.getAffectors().get(0));
//	}

}