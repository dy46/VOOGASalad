package game_engine.affectors;


import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;
<<<<<<< HEAD
=======
import game_engine.game_elements.Unit;
import game_engine.properties.UnitProperties;
>>>>>>> 2644dea77f1258a7f847453cb880537379bb2d20

public class ConstantHealthDamageAffector extends HealthDamageAffector{

    public ConstantHealthDamageAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
        super(functions, engineWorkspace);
    }

<<<<<<< HEAD
}
=======
    @Override
    public void apply(Unit u) {
        super.apply(u);
        double damage = getBaseNumbers().get(0) + getFunctions().get(0).evaluate(getElapsedTime());
        u.getProperties().getHealth().decrementValue(damage);   
    }

}
>>>>>>> 2644dea77f1258a7f847453cb880537379bb2d20
