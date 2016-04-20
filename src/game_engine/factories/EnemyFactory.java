package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorTimeline;
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


public class EnemyFactory {

    private AffectorLibrary myAffectorLibrary;
    private TimelineLibrary myTimelineLibrary;

    public EnemyFactory (AffectorLibrary affectorLibrary, TimelineLibrary timelineLibrary) {
        this.myAffectorLibrary = affectorLibrary;
        this.myTimelineLibrary = timelineLibrary;
    }
    
    public Unit createPathFollowPositionMoveEnemy (String name, List<Branch> branches) {
        Unit e = createSpecifiedEnemy(name, "PathFollow", "PositionMove");
        e.getProperties().setMovement(new Movement(branches));
        return e;
    }

    public Unit createAIEnemy (String name, Branch startingBranch) {
        Unit e = createSpecifiedEnemy(name, "AIPath", "Follow");
        e.getProperties().setMovement(new Movement(Arrays.asList(startingBranch)));
        return e;
    }
    
    public Unit createRandomEnemy(String name, Branch startingBranch){
		Unit e = createSpecifiedEnemy(name, "RandomPath", "Follow");
		e.getProperties().setMovement(new Movement(Arrays.asList(startingBranch)));
		return e;
	}

    public Unit createSpecifiedEnemy (String name, String behavior, String property) {
        Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
        moveAffector.setTTL(Integer.MAX_VALUE);
        AffectorTimeline timeline1 = new AffectorTimeline(moveAffector);
        // Field[] fields = Unit.class.getDeclaredFields();
        Unit e1 = new Unit(name, Arrays.asList(timeline1), 3);
        // forward.addEndEvent(new EndEvent(getFieldByName(fields, "hasCollided"), e1, 1, "=="));
        Health health = new Health(50);
        Velocity velocity = new Velocity(0.5, 90);
        List<Position> l1 = new ArrayList<>();
        l1.add(new Position(0, 0));
        l1.add(new Position(30, 0));
        l1.add(new Position(30, 30));
        l1.add(new Position(0, 30));
        Bounds b = new Bounds(l1);
        State st = new State(2);
        Branch p2 = new Branch(0);
        p2.addPosition(new Position(0, 30));
        p2.addPosition(new Position(200, 30));
        p2.addPosition(new Position(200, 200));
        p2.addPosition(new Position(400, 200));
        p2.addPosition(new Position(400, 525));
        Movement movement = new Movement(Arrays.asList(p2));
        UnitProperties properties =
                new UnitProperties(health, null, velocity, b, null, new Position(0, 30), null,
                                   st, movement);
        e1.setProperties(properties);
        e1.setTTL(1000000);
        e1.setDeathDelay(3);
        return e1;
    }
}
