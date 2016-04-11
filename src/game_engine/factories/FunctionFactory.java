package game_engine.factories;

import java.util.ArrayList;
import java.util.List;

import game_engine.functions.Constant;
import game_engine.functions.Function;
import game_engine.functions.Term;
import game_engine.functions.Variable;
import game_engine.libraries.FunctionLibrary;

public class FunctionFactory {

	private FunctionLibrary myFunctionLibrary;

	public FunctionFactory(){
		myFunctionLibrary = new FunctionLibrary(this);
		setupSpecialConstants();
		setupDefaultStrengths();
		setupDefaultTypes();
	}

	public void createFunction(String equation){

	}

	public Function createFunction(String type, String str){
		String name = getName(type, str);
		if(myFunctionLibrary.getFunction(name) != null){
			return myFunctionLibrary.getFunction(name);
		}
		List<String> uniqueIdentifier = new ArrayList<>();
		uniqueIdentifier.add(type);
		uniqueIdentifier.add(str);
		List<Term> terms = myFunctionLibrary.getFunctionType(type);
		if(terms == null){
			// TODO: throw Womp error "Function type not implemented yet"
		}
		Constant strength = myFunctionLibrary.getStrength(str);
		if(strength == null){
			// TODO: throw Womp error "Strength type not implemented yet"
		}
		for(Term term : terms){
			List<Constant> constants = copyConstantList(term.getConstants());
			for(Constant c: constants){
				if(!c.isSpecial()){
					c.setValue(strength.evaluate());
				}
			}
			term.setConstants(constants);
		}
		Function newFunction = new Function(getName(type, str), terms);
		myFunctionLibrary.addFunction(newFunction);
		return newFunction;
	}

	public Function createConstantFunction(double constant){
		List<Term> terms = new ArrayList<>();
		Constant c = new Constant(constant, 1);
		Term constantTerm = new Term(c);
		terms.add(constantTerm);
		return new Function(terms);
	}

	public Function createExpIncrFunction(String type){
		return myFunctionLibrary.getFunction(getName("ExpIncr", type));
	}

	public Function createExpDecrFunction(String type){
		return myFunctionLibrary.getFunction(getName("ExpDecr", type));
	}

	public Function createRandomConstantFunction(){
		double randomConstant = 100*Math.random();
		return createConstantFunction(randomConstant);
	}

	public Function createSingleTermFunction(Constant constant, Variable variable){
		List<Term> terms = new ArrayList<>();
		terms.add(new Term(constant, variable));
		return new Function(terms);
	}

	public Function createLinearFunction(Constant constant, Term term){
		List<Term> terms = new ArrayList<>();
		terms.add(new Term(constant));
		terms.add(term);
		return new Function(terms);
	}

	public String getName(String type, String str){
		return type + " " + str;
	}

	private void setupSpecialConstants(){
		Constant gravity = new Constant("Gravity", -9.8);
		Constant pi = new Constant("Pi", Math.PI);
		myFunctionLibrary.addSpecialConstant(gravity);
		myFunctionLibrary.addSpecialConstant(pi);
	}

	public void setupDefaultTypes() {
		setupExpIncrFunction("Strong");
		setupExpIncrFunction("Moderate");
		setupExpIncrFunction("Weak");
		setupExpDecrFunction("Strong");
		setupExpDecrFunction("Moderate");
		setupExpDecrFunction("Weak");
	}

	public void setupDefaultStrengths(){
		Constant strong = new Constant(1.2,1);
		Constant moderate = new Constant(1.1, 1);
		Constant weak = new Constant(1.05, 1);
		myFunctionLibrary.addStrength("Strong", strong);
		myFunctionLibrary.addStrength("Moderate", moderate);
		myFunctionLibrary.addStrength("Weak", weak);
	}

	private void setupExpFunction(String strength, int sign){
		List<Constant> expConsts = new ArrayList<>();
		Constant const_proportionality = new Constant(myFunctionLibrary.getStrength(strength).evaluate() * sign, 1);
		expConsts.add(const_proportionality);
		List<Variable> expVars = new ArrayList<>();
		expVars.add(new Variable("t", 1));
		Term expTerm1 = new Term(expVars, expConsts);
		List<Term> expTerms = new ArrayList<>();
		expTerms.add(expTerm1);
		String type = "";
		if(sign == 1)
			type = "ExpIncr";
		else if(sign == -1)
			type = "ExpDecr";
		else{
			// TODO: throw Womp Exception "Only exponentially increasing and decreasing functions supported"
		}
		myFunctionLibrary.addFunctionType(type, expTerms);
		if(sign==1){
			Function expIncr = new Function(getName(type, strength), expTerms);
			myFunctionLibrary.addFunction(expIncr);
		}
		if(sign==-1){
			Function expDecr = new Function(getName(type, strength), expTerms);
			myFunctionLibrary.addFunction(expDecr);
		}
		
	}

	private void setupExpIncrFunction(String strength){
		setupExpFunction(strength, 1);
	}

	private void setupExpDecrFunction(String strength){
		setupExpFunction(strength, -1);
	}

	private List<Constant> copyConstantList(List<Constant> constants){
		List<Constant> constantsCopy = new ArrayList<Constant>();
		for(Constant c : constants){
			Constant cCopy = new Constant(c.getValue(), c.getPower());
			constantsCopy.add(cCopy);
		}
		return constantsCopy;
	}

	public FunctionLibrary getFunctionLibrary(){
		return myFunctionLibrary;
	}

}