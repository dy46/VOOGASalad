package game_engine.affectors;


import java.util.List;
import game_engine.functions.Function;
import game_engine.properties.Bounds;
import game_engine.functions.Function;
import game_engine.games.GameEngineInterface;

public class ConstantHealthDamageAffector extends HealthDamageAffector{

    public ConstantHealthDamageAffector(List<Function> functions){
        super(functions);
    }
    
}