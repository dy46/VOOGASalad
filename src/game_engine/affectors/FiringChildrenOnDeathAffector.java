package game_engine.affectors;

import java.util.List;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Property;

public class FiringChildrenOnDeathAffector extends Affector{

    Affector firingChildrenAffector;
    private boolean firstApplication;
            
    public FiringChildrenOnDeathAffector (AffectorData data) {
        super(data);
        firingChildrenAffector = new FiringChildrenAffector(data);
        firstApplication = true;
    }

    @Override
    public void apply (List<Function> functions, Property property, Unit u) {
        if(!u.isAlive()) {
            if(firstApplication) {
                firingChildrenAffector.apply(functions, property, u);
                u.getProperties().getChildren().stream().forEach(c -> 
                c.getProperties().setMovement(u.getProperties().getMovement().copyMovement()));
            }
        } 
    }

}
