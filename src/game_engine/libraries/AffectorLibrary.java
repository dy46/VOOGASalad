package game_engine.libraries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import game_engine.affectors.Affector;

public class AffectorLibrary {

	private HashMap<List<String>, Affector> myAffectors;
	
	public AffectorLibrary(){
		myAffectors = new HashMap<>();
	}
	
	public Affector getAffector(String property, String effect){
		return myAffectors.get(getIdentifier(property, effect));
	}
	
	public void addAffector(String property, String effect, Affector affector){
		myAffectors.put(getIdentifier(property, effect), affector);
	}
	
	private List<String> getIdentifier(String property, String effect){
		return Arrays.asList(property, effect);
	}
	
}