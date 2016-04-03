package game_engine.affectors;

import java.util.List;
import game_engine.properties.UnitProperties;

public class PositionMoveAffector extends Affector {

    public PositionMoveAffector (List<Double> baseNumbers, int timeToLive) {
        super(baseNumbers, timeToLive);
    }

    @Override
    public void apply (UnitProperties properties) {
        properties.getPosition().addToXY(getBaseNumbers().get(0), 
                                         getBaseNumbers().get(1));    
    }
    
}
