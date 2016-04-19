package game_engine.affectors;

import game_engine.game_elements.Unit;
import game_engine.properties.State;


public class StateChangeAffector extends Affector {
    
    public StateChangeAffector(AffectorData data){
        super(data);
    }
    
    @Override
    public void apply(Unit u) {
        super.apply(u);
        State state = u.getProperties().getState();
        if(!state.getValue().equals("Dying")) {
            state.changeState((int) this.getData().getFunctions().get(0).get(0).evaluate(getElapsedTime()));
        }
    }

}