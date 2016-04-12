package game_engine.affectors;

import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.properties.State;
import game_engine.properties.UnitProperties;


public class StateChangeAffector extends Affector {
    
    public StateChangeAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
        super(functions, engineWorkspace);
    }
    
    public void apply(UnitProperties unitProperties) {
        State state = unitProperties.getState();
        if(!state.getValue().equals("Dying")) {
            state.changeState(this.getBaseNumbers().get(0).intValue());
        }
    }

}
