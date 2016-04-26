package game_engine.affectors;


public class BasicSetAffector extends BasicAffector {

    public BasicSetAffector (AffectorData data) {
        super(data, Operator.EQUALS);
    }

}