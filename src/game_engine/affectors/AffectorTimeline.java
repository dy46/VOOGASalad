package game_engine.affectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.game_elements.Unit;

public class AffectorTimeline {

    private List<Affector> myAffectors;

    public AffectorTimeline(List<Affector> affectors){
        this.myAffectors = affectors;
    }

    public AffectorTimeline(Affector affector){
        this.myAffectors = Arrays.asList(affector);
    }

    public void apply(Unit unit){
        if(myAffectors.size() == 0)
            return;
        update();
        myAffectors.stream().filter(ab -> ab.getTTL() > ab.getElapsedTime()).forEach(ab -> ab.apply(unit));
    }

    public AffectorTimeline copyTimeline() {
        return new AffectorTimeline(myAffectors.stream().map(a -> a.copyAffector()).collect(Collectors.toList()));
    }

    public List<Affector> getAffectors() {
        return myAffectors;
    }

    public void update(){
        for(Affector a : myAffectors){
            if(a.getTTL() <= a.getElapsedTime())
                a.setElapsedTimeToDeath();
        }
    }

}