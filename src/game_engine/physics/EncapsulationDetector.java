package game_engine.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.GameEngineInterface;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;
import game_engine.properties.Bounds;


public class EncapsulationDetector {

    private GameEngineInterface myEngine;

    public EncapsulationDetector (GameEngineInterface engine) {
        myEngine = engine;
    }

    public void resolveEncapsulations (List<Unit> terrains) {
        myEngine.getUnitController().getUnitType("Enemy").forEach(t -> terrainHandling(t, terrains));
        myEngine.getUnitController().getUnitType("Tower").forEach(t -> terrainHandling(t, terrains));
    }

    public List<Unit> getUnitsInRange (Bounds range) {
        List<Unit> units = myEngine.getUnitController().getPlacedUnits();
        List<Unit> inRange = new ArrayList<>();
        for (Unit u : units) {
            if (encapsulates(u, range)) {
                inRange.add(u);
            }
        }
        return inRange;
    }

    public boolean encapsulates (Unit inner, Unit outer) {
        return EncapsulationChecker.encapsulates(inner.getProperties().getBounds()
                .getUseableBounds(inner.getProperties().getPosition()),
                                                 outer.getProperties().getBounds()
                                                         .getUseableBounds(outer.getProperties()
                                                                 .getPosition()));
    }

    public boolean encapsulates (Unit inner, Bounds range) {
        return EncapsulationChecker.encapsulates(inner.getProperties().getBounds()
                .getUseableBounds(inner.getProperties().getPosition()),
                                                 range.getPositions());
    }

    private void terrainHandling (Unit unit, List<Unit> terrains) {
        for (int i = 0; i < terrains.size(); i++) {
            if ((!(unit == terrains.get(i)) && encapsulates(unit, terrains.get(i)))) {
                if (unit.isVisible()) {
                    List<Affector> newAffectorsToApply =
                            terrains.get(i).getAffectorsToApply().stream()
                                    .map(a -> a.copyAffector()).collect(Collectors.toList());
                    unit.addAffectors(newAffectorsToApply);
                }
            }
        }
    }

}
