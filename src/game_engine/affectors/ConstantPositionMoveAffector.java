package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;

public class ConstantPositionMoveAffector extends Affector{
    
    public ConstantPositionMoveAffector(AffectorData data){
        super(data);
    }
    
    @Override
    public void apply(Unit unit) {
        super.apply(unit);
        double speed = unit.getProperties().getVelocity().getSpeed();
        double direction = unit.getProperties().getVelocity().getDirection();
        double xDir = Math.sin(Math.toRadians(direction));
        double yDir = Math.cos(Math.toRadians(direction));
        unit.getProperties().getPosition().addToXY(speed*xDir, speed*yDir);
    }
}
