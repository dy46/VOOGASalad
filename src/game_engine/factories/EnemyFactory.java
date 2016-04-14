package game_engine.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import auth_environment.buildingBlocks.EnemyBuildingBlock;
import game_engine.affectors.Affector;
import game_engine.game_elements.Enemy;
import game_engine.libraries.AffectorLibrary;
import game_engine.game_elements.Path;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Position;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;

public class EnemyFactory {

	private AffectorLibrary myAffectorLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}
	
	public Enemy defineEnemyModel(EnemyBuildingBlock eBlock){
		Affector moveAffector = myAffectorLibrary.getAffector(eBlock.getMyProperty(), eBlock.getMyBehavior());
		moveAffector.setTTL(Integer.MAX_VALUE);
		Enemy e = new Enemy(eBlock.getMyName(), Collections.singletonList(moveAffector), 3); 
		Health health = eBlock.getMyHealth();
		Velocity velo = eBlock.getMyVelocity(); 
		Bounds b = eBlock.getMyBounds();
		State st = eBlock.getMyState(); 
//		Path p = eBlock.getMyPath(); 										This value is gonna be null for now
		UnitProperties properties = new UnitProperties(health, null, null, velo, b, null, null, st, null);
		e.setProperties(properties);
		e.setTTL(100000);
		e.setDeathDelay(3);
		return e;
	}

	public Enemy createPathFollowPositionMoveEnemy(String name){
		return createSpecifiedEnemy(name, "PathFollow", "PositionMove");
	}

	public Enemy createSpecifiedEnemy(String name, String behavior, String property) {
		Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
		moveAffector.setTTL(Integer.MAX_VALUE);
		Enemy e1 = new Enemy(name, Collections.singletonList(moveAffector), 3);
		Health health = new Health(30);
		Velocity velocity = new Velocity(0.5, 90);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(30,0));
		l1.add(new Position(30,30));
		l1.add(new Position(0,30));
		Bounds b = new Bounds(l1);
		State st = new State("Moving");
		Path p2 = new Path("Something here");
		p2.addPosition(new Position(0,30));
		p2.addPosition(new Position(200, 30));
		p2.addPosition(new Position(200, 200));
		p2.addPosition(new Position(400, 200));
		p2.addPosition(new Position(400, 525));
		UnitProperties properties = new UnitProperties(health, null, null, velocity, b, new Position(0,30), null, st, p2);
		e1.setProperties(properties);
		e1.setTTL(1000000);
		e1.setDeathDelay(3);
		return e1;
	}


}