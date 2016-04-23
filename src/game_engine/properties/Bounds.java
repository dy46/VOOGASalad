package game_engine.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Bounds extends Property {

    private List<Position> myPositions;

    public Bounds (List<Position> positions) {
        this.myPositions = positions;
    }

    public List<Position> getPositions () {
        return myPositions;
    }

    public void setPositions (List<Position> positions) {
        this.myPositions = positions;
    }

    public Bounds copyBounds () {
        List<Position> newPositions =
                this.getPositions().stream().map(p -> p.copyPosition())
                        .collect(Collectors.toList());
        return new Bounds(newPositions);
    }

    public String toString () {
        String str = "";
        for (Position pos : myPositions) {
            str += pos.getX() + " " + pos.getY() + "\n";
        }
        return str;
    }

    @Override
    public List<Double> getValues () {
        List<Double> values = new ArrayList<>();
        myPositions.stream().forEach(p -> values.addAll(p.getValues()));
        return values;
    }

    @Override
    public void setValues (List<Double> values) {
       for(int i = 0; i < myPositions.size(); i++) {
           myPositions.get(i).setValues(Arrays.asList(values.get(2*i), values.get(2*i+1)));
       }
    }

}
