package game_engine.affectors;

import game_engine.Operator;

public class BasicIncrementAffector extends BasicAffector {

    public BasicIncrementAffector (AffectorData data) {
        super(data, Operator.SUM);
    }
}
