package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;

public class RangePathFollowPositionMoveAffector extends SingleTrackRangeAffector {

    PathFollowPositionMoveAffector affector;
    
    public RangePathFollowPositionMoveAffector (AffectorData data) {
        super(data);
        affector = new PathFollowPositionMoveAffector(data);
    }

    @Override
    public void firstApply (Unit u, Unit tracked) {
        affector.pathFollow(u); 
    }

    @Override
    public void futureApply (Unit u, Unit tracked) {
        firstApply(u, tracked);
    }

}
