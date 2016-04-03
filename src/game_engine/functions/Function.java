package game_engine.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a discrete function based on any number of independent variable as arguments.
 * A function is made up of Terms (see Term.java), which are themselves made up of constants and variables.
 * @author adamtache
 *
 */
public class Function {
	
	private List<Term> myTerms;
	
	public Function(String function){
		myTerms = new ArrayList<>();
		
	}
	
	public double evaluate(int time){
		double evaluation = 0;
		for(Term term : myTerms){
			evaluation += term.evaluate(time);
		}
		return evaluation;
	}
	
}