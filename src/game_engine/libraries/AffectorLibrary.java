package game_engine.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import game_engine.affectors.Affector;

public class AffectorLibrary {

	private HashMap<List<String>, Affector> myAffectors;
	private HashMap<String, Affector> namesToAffector;
	private List<Affector> allAffectors;
	
	public AffectorLibrary(){
		myAffectors = new HashMap<>();
		allAffectors = new ArrayList<>();
		namesToAffector = new HashMap<>();
	}
	
	public Affector getAffector(String property, String effect){
		Affector a = myAffectors.get(getIdentifier(property, effect)).copyAffector();
		allAffectors.add(a);
		return a;
	}
	
	public Affector getAffector(String name) {
	    Affector a = myAffectors.get(name);
	    allAffectors.add(a);
	    return a;
	}
	
	public void addAffector(String name, Affector affector) {
		namesToAffector.put(name, affector);
	}
	
	public void addAffector(String property, String effect, Affector affector){
		myAffectors.put(getIdentifier(property, effect), affector);
		namesToAffector.put(property + effect, affector);
	}

	private List<String> getIdentifier(String property, String effect){
		return Arrays.asList(property, effect);
	}
	
	public List<Affector> getAffectors(){
		return allAffectors;
	}
	
	public List<String> getAffectorNames() {
	    return namesToAffector.keySet().stream().collect(Collectors.toList());
	}
	
	
}