package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public class ConstantHealthDamageAffector extends Affector{

    public ConstantHealthDamageAffector (List<Function> functions) {
        super(functions);
    }
    
    public void apply(UnitProperties properties) {
        super.apply(properties);
        double damage = getBaseNumbers().get(0) * getFunctions().get(0).evaluate(getElapsedTime());
        properties.getHealth().decrementValue(damage);
        
    }

}
