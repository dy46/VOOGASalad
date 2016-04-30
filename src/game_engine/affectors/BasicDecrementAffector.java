package game_engine.affectors;

import game_engine.Operator;

public class BasicDecrementAffector extends BasicAffector {

    public BasicDecrementAffector (AffectorData data) {
        super(data, Operator.DIFFERENCE);
    }

}
