package game_engine.affectors;


import java.util.List;
import game_engine.IPlayerEngineInterface;
import game_engine.functions.Function;

public class ExpIncrHealthDamageAffector extends Affector{

	public ExpIncrHealthDamageAffector(List<Function> functions, IPlayerEngineInterface engineWorkspace){
        super(functions, engineWorkspace);
    }

}