package game_engine.affectors;

import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;


public class StateChangeAffector extends Affector {
    
    public StateChangeAffector(List<Function> functions){
        super(functions);
    }
    
    @Override
    public void apply(Unit u) {
        super.apply(u);
        State state = u.getProperties().getState();
        if(!state.getValue().equals("Dying")) {
            state.changeState(this.getBaseNumbers().get(0).intValue());
        }
    }

}
