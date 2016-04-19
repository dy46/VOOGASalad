package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;

public class RangeConstantPositionMoveAffector extends SingleTrackRangeAffector{
    
    ConstantPositionMoveAffector affector;
    
    public RangeConstantPositionMoveAffector (List<Function> functions) {
        super(functions);
        affector = new ConstantPositionMoveAffector(functions);
    }

    @Override
    public void firstApply (Unit u, Unit tracked) {
        affector.apply(u);
        
    }

    @Override
    public void futureApply (Unit u, Unit tracked) {
        firstApply(u, tracked);
        
    }

}
