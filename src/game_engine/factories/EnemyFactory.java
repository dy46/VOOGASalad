package game_engine.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorLibrary;
import game_engine.game_elements.Enemy;
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
            Enemy e1 = new Enemy(name, Collections.singletonList(moveAffector), 3);
            Health health = new Health(30);
            Position position = new Position(0, 200);
            Velocity velocity = new Velocity(0.5, 90);
            List<Position> l1 = new ArrayList<>();
            l1.add(new Position(0,0));
            l1.add(new Position(30,0));
            l1.add(new Position(30,30));
            l1.add(new Position(0,30));
            Bounds b = new Bounds(l1);
            State st = new State("Moving");
            UnitProperties properties = new UnitProperties(health, null, null, velocity, b, position, null, st);
            e1.setProperties(properties);
            e1.setTTL(1000000);
            e1.setDeathDelay(3);
            return e1;
        }
        

}