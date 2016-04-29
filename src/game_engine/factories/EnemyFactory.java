package game_engine.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.libraries.AffectorLibrary;
import game_engine.game_elements.Branch;
import game_engine.properties.Bounds;
import game_engine.properties.Health;
import game_engine.properties.Mass;
import game_engine.properties.Movement;
import game_engine.properties.Position;
import game_engine.properties.Price;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;
import game_engine.properties.Velocity;


public class EnemyFactory {

    private AffectorLibrary myAffectorLibrary;

    public EnemyFactory (AffectorLibrary affectorLibrary) {
        this.myAffectorLibrary = affectorLibrary;
    }
    
    public Unit createPathFollowPositionMoveEnemy (String name, List<Branch> branches) {
        Unit e = createSpecifiedEnemy(name, "PathFollow", "PositionMove");
        e.getProperties().setMovement(new Movement(branches, branches.get(0).getFirstPosition()));
        return e;
    }

    public Unit createAIEnemy (String name, Position spawn) {
        Unit e = createSpecifiedEnemy(name, "AIPath", "Follow");
        e.getProperties().setMovement(new Movement(spawn));
        return e;
    }
    
    public Unit createRandomEnemy(String name, Position spawn){
		Unit e = createSpecifiedEnemy(name, "RandomPath", "Follow");
		e.getProperties().setMovement(new Movement(spawn));
        e.getProperties().setPosition(spawn);
		return e;
	}
    
    public Unit createSpecifiedEnemy (String name, String behavior, String property) {
        Affector moveAffector = myAffectorLibrary.getAffector(behavior, property);
        moveAffector.setTTL(Integer.MAX_VALUE);
        // Field[] fields = Unit.class.getDeclaredFields();
        Unit e1 = new Unit(name, Arrays.asList(moveAffector), 3);
        // forward.addEndEvent(new EndEvent(getFieldByName(fields, "hasCollided"), e1, 1, "=="));
        Health health = new Health(50);
        Velocity velocity = new Velocity(0.5, 90);
        List<Position> l1 = new ArrayList<>();
        l1.add(new Position(-20, -20));
		l1.add(new Position(20, -20));
		l1.add(new Position(20, 20));
		l1.add(new Position(-20, 20));
        Bounds b = new Bounds(l1);
        State st = new State(2);
        Price price = new Price(30);
        UnitProperties properties =
                new UnitProperties(health, null, velocity, b, null, price,
                                   st, new Movement(new Position(0, 0)), new Mass(1));
        e1.setProperties(properties);
        e1.setTTL(1000000);
        e1.setDeathDelay(3);
        return e1;
    }
}
