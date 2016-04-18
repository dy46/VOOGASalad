package game_engine.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import game_engine.affectors.Affector;

public class AffectorLibrary {

	private HashMap<List<String>, Affector> myAffectors;
	private List<Affector> allAffectors;
	
	public AffectorLibrary(){
		myAffectors = new HashMap<>();
		allAffectors = new ArrayList<>();
	}
	
	public Affector getAffector(String property, String effect){
		System.out.println("GOT AFFECTOR: " + myAffectors.get(getIdentifier(property, effect)));
		Affector a = myAffectors.get(getIdentifier(property, effect)).copyAffector();
		allAffectors.add(a);
		return a;
	}
	
	public void addAffector(String property, String effect, Affector affector){
		myAffectors.put(getIdentifier(property, effect), affector);
	}
	
	private List<String> getIdentifier(String property, String effect){
		return Arrays.asList(property, effect);
	}
	
	public List<Affector> getAffectors(){
		return allAffectors;
	}
	
}