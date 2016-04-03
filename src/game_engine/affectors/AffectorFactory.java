package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;

public class AffectorFactory {
    
    public static final String PACKAGE = "game_engine.affectors.";
    public static final String BASE = "Affector";
    
//    public Affector constructAffector(String property, String effect, List<Double> baseNumbers, int TTL) {
//        Affector affector = null;
//        try {
//            affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
//                    .getConstructor(List.class, int.class).newInstance(baseNumbers, TTL);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return affector;
//    }
    
    public Affector constructAffector(String property, String effect, List<Function> functions, int TTL){
    	Affector affector = null;
        try {
            affector = (Affector) Class.forName(PACKAGE + property + effect + BASE)
                    .getConstructor(List.class, int.class).newInstance(functions, TTL);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return affector;
    }
    
}