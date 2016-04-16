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
		Affector a = myAffectors.get(0);
		a.apply(unit);
		if(a.getTTL() == a.getElapsedTime()){
			myAffectors.remove(a);
		}
	}

	public AffectorTimeline copyTimeline() {
		return new AffectorTimeline(myAffectors.stream().map(a -> a.copyAffector()).collect(Collectors.toList()));
	}

	public List<Affector> getAffectors() {
		return myAffectors;
	}

}