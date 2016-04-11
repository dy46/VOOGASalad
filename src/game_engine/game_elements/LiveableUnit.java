package game_engine.game_elements;

import java.util.List;
import game_engine.affectors.Affector;


public abstract class LiveableUnit extends Unit {

    public LiveableUnit (String name, List<Affector> affectors, int numFrames) {
        super(name, affectors, numFrames);
    }

    public boolean isAlive () {
        return getProperties().getHealth().getValue() > 0;
    }

    public void update (Unit unit) {
        super.update();
        if (!isAlive()) {
            unit.setElapsedTimeToDeath();
        }
    }

}
