package game_engine.affectors;


public class BasicIncrementAffector extends BasicAffector {

    public BasicIncrementAffector (AffectorData data) {
        super(data, Operator.SUM);
    }
}
