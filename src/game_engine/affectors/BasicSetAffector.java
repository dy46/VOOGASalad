package game_engine.affectors;

import game_engine.Operator;

public class BasicSetAffector extends BasicAffector {

    public BasicSetAffector (AffectorData data) {
        super(data, Operator.EQUALS);
    }

}