package game_engine.factories;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import auth_environment.buildingBlocks.BuildingBlock;
import auth_environment.buildingBlocks.EnemyBuildingBlock;
import auth_environment.paths.PathNode;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.TimelineLibrary;
import game_engine.game_elements.Branch;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;
import game_engine.timelines.EndEvent;
import game_engine.timelines.Timeline;

public class EnemyFactory {

	private AffectorLibrary myAffectorLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary){
		this.myAffectorLibrary = affectorLibrary;
	}
	
//	public Enemy defineEnemyModel(BuildingBlock block){
//		EnemyBuildingBlock eBlock = (EnemyBuildingBlock) block;
//		Affector moveAffector = myAffectorLibrary.getAffector(eBlock.getMyProperty(), eBlock.getMyBehavior());
//		moveAffector.setTTL(Integer.MAX_VALUE);
//		Enemy e = new Enemy(eBlock.getMyName(), Arrays.asList(new Timeline(Arrays.asList(Collections.singletonList(moveAffector)))), 3); 
//		Health health = eBlock.getMyHealth();
//		Velocity velo = eBlock.getMyVelocity(); 
//		Bounds b = eBlock.getMyBounds();
//		State st = eBlock.getMyState(); 
////		Path p = eBlock.getMyPath(); 										This value is gonna be null for now
//		UnitProperties properties = new UnitProperties(health, null, null, velo, b, null, null, null, st, null);
////		Branch p = eBlock.get(); 										This value is gonna be null for now
//		UnitProperties properties = new UnitProperties(health, null, null, velo, b, null, null, st, null);
//		e.setProperties(properties);
//		e.setTTL(100000);
//		e.setDeathDelay(3);
//		return e;
//	}

	public Enemy createPathFollowPositionMoveEnemy(String name, List<Branch> branches){
		Enemy e = createSpecifiedEnemy(name, "PathFollow", "PositionMove");
		e.getProperties().setMovement(new Movement(branches));
		return e;
	}
	
	public Enemy createAIEnemy(String name, Branch startingBranch){
		Enemy e = createSpecifiedEnemy(name, "AIPath", "Follow");
		e.getProperties().setMovement(new Movement(Arrays.asList(startingBranch)));
		return e;
	}
	
	public Enemy createRandomEnemy(String name, Branch startingBranch){
		Enemy e = createSpecifiedEnemy(name, "RandomPath", "Follow");
		e.getProperties().setMovement(new Movement(Arrays.asList(startingBranch)));
		return e;
	}

	public Enemy createSpecifiedEnemy(String name, String behavior, String property) {
		Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
		moveAffector.setTTL(Integer.MAX_VALUE);
		AffectorTimeline timeline1 = new AffectorTimeline(moveAffector);
//		Field[] fields = Unit.class.getDeclaredFields();
		Enemy e1 = new Enemy(name, Arrays.asList(timeline1), 3);
//		forward.addEndEvent(new EndEvent(getFieldByName(fields, "hasCollided"), e1, 1, "=="));
		Health health = new Health(50);
		Velocity velocity = new Velocity(0.5, 90);
		List<Position> l1 = new ArrayList<>();
		l1.add(new Position(0,0));
		l1.add(new Position(30,0));
		l1.add(new Position(30,30));
		l1.add(new Position(0,30));
		Bounds b = new Bounds(l1);
		State st = new State("Moving");
		Branch p2 = new Branch("Something here");
		p2.addPosition(new Position(0,30));
		p2.addPosition(new Position(200, 30));
		p2.addPosition(new Position(200, 200));
		p2.addPosition(new Position(400, 200));
		p2.addPosition(new Position(400, 525));
		Movement movement = new Movement(Arrays.asList(p2));
		UnitProperties properties = new UnitProperties(health, null, null, velocity, b, null, new Position(0,30), null, st, movement, null);
		e1.setProperties(properties);
		e1.setTTL(1000000);
		e1.setDeathDelay(3);
		return e1;
	}

//	private Field getFieldByName(Field[] fields, String name){
//		for(Field field : fields){
//			if(field.getName().equals(name))
//				return field;
//		}
//		return null;
//	}

}