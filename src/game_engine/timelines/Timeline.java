package game_engine.timelines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;
import game_engine.game_elements.Unit;

public class Timeline {

	private List<List<Affector>> myAffectors;

	public Timeline(List<List<Affector>> affectors){
		this.myAffectors = affectors;
	}

	public Timeline(Affector affector){
		this.myAffectors = Arrays.asList(Arrays.asList(affector));
	}

	public void apply(Unit unit){
		if(myAffectors.size() == 0)
			return;
		List<Affector> affectors = myAffectors.get(0);
		affectors.stream().forEach(a -> a.apply(unit));
		affectors.stream().filter(a -> a.getTTL() <= a.getElapsedTime());
	}

	public Timeline copyTimeline() {
		List<List<Affector>> affectorsCopy = new ArrayList<List<Affector>>();
		for(List<Affector> affectorList : myAffectors){
			affectorsCopy.add(affectorList.stream().map(a -> a.copyAffector()).collect(Collectors.toList()));
		}
		return new Timeline(affectorsCopy);
	}

	public List<List<Affector>> getAffectors() {
		return myAffectors;
	}

}