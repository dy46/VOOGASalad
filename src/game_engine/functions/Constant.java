package game_engine.functions;

/**
 * This class represents a Constant for a Function and can include a power.
 * For example, a Constant 5 with power 3 evaluates to 5^3 = 125.
 * @author adamtache
 *
 */
public class Constant {

	private long myValue;
	private long myPower;
	
	public Constant(long value, long power){
		this.myValue = value;
		this.myPower = power;
	}
	
	public long evaluate(){
		return (long) Math.pow(myValue, myPower);
	}
	
}