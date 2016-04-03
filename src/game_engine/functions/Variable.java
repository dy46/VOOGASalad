package game_engine.functions;

/**
 * This class represents an (independent) Variable for a Function and can include a power.
 * For example, a Variable x with power 3 is equivalent to x^3.
 * Variables are evaluated by index, which plugs in the index as the variable value.
 * @author adamtache
 *
 */
public class Variable {
	
	private String myVariable;
	private long myPower;
	
	public Variable(String variable, long power){
		this.myVariable = variable;
		this.myPower = power;
	}

	public long evaluate(int index) {
		return (long) Math.pow(index, myPower);
	}
	
	public String getVariable(){
		return myVariable;
	}

}