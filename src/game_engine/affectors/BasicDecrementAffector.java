package game_engine.affectors;


public class BasicDecrementAffector extends BasicAffector {

    public BasicDecrementAffector (AffectorData data) {
        super(data, Operator.DIFFERENCE);
    }

}
