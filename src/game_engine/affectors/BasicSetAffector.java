package game_engine.affectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import game_engine.functions.Function;
import game_engine.game_elements.Unit;
import game_engine.properties.Property;

public class BasicSetAffector extends Affector {

    public BasicSetAffector (AffectorData data) {
        super(data);
    }

    @Override
    public void apply (List<Function> functions, Property property, Unit u) {
        List<Double> values = new ArrayList<>();
        IntStream.range(0, property.getValues().size())
                 .forEach(i -> values.add(functions.get(i).evaluate(getElapsedTime())));
        property.setValues(values);
    }

}
