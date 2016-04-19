package game_engine.affectors;

import java.util.List;
import java.util.stream.Collectors;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;


public class FiringChildrenAffector extends Affector {
    
    
    public FiringChildrenAffector (List<Function> functions) {
        super(functions);
    }

    public void apply (Unit u) {
        super.apply(u);
        if (getElapsedTime() % getBaseNumbers().get(0) == 0) {
            List<Unit> newChildren =
                    u.getChildren().stream().map(p -> p.copyUnit()).collect(Collectors.toList());
            newChildren.forEach(p -> {
                p.getProperties().setPosition(u.getProperties().getPosition().getX(),
                                              u.getProperties().getPosition().getY());
                getWS().getProjectiles().add(p);
                p.addParent(u);
            });
        }
    }

}
