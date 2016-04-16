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
	private TimelineLibrary myTimelineLibrary;

	public EnemyFactory(AffectorLibrary affectorLibrary, TimelineLibrary timelineLibrary){
		this.myAffectorLibrary = affectorLibrary;
		this.myTimelineLibrary = timelineLibrary;
	}
	
	public Enemy defineEnemyModel(BuildingBlock block){
		EnemyBuildingBlock eBlock = (EnemyBuildingBlock) block;
		Affector moveAffector = myAffectorLibrary.getAffector(eBlock.getMyProperty(), eBlock.getMyBehavior());
		moveAffector.setTTL(Integer.MAX_VALUE);
		Enemy e = new Enemy(eBlock.getMyName(), Arrays.asList(new Timeline(Arrays.asList(Collections.singletonList(moveAffector)))), 3); 
		Health health = eBlock.getMyHealth();
		Velocity velo = eBlock.getMyVelocity(); 
		Bounds b = eBlock.getMyBounds();
		State st = eBlock.getMyState(); 
//		Branch p = eBlock.get(); 										This value is gonna be null for now
		UnitProperties properties = new UnitProperties(health, null, null, velo, b, null, null, st, null);
		e.setProperties(properties);
		e.setTTL(100000);
		e.setDeathDelay(3);
		return e;
	}

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

	public Enemy createSpecifiedEnemy(String name, String behavior, String property) {
		Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
		moveAffector.setTTL(Integer.MAX_VALUE);
		Timeline timeline1 = new Timeline(moveAffector);
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
		UnitProperties properties = new UnitProperties(health, null, null, velocity, b, new Position(0,30), null, st, null);
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