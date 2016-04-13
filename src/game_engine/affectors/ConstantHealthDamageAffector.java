package game_engine.affectors;


import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.UnitProperties;

public class ConstantHealthDamageAffector extends Affector{

    public ConstantHealthDamageAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
        super(functions, engineWorkspace);
    }

    @Override
    public void apply(Unit u) {
        super.apply(u);
        double damage = getBaseNumbers().get(0) + getFunctions().get(0).evaluate(getElapsedTime());
        u.getProperties().getHealth().decrementValue(damage);   
    }

}
