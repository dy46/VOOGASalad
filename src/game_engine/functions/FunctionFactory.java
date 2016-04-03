package game_engine.functions;

import java.util.ArrayList;
import java.util.List;

public class FunctionFactory {
	
	private FunctionLibrary myFunctionLibrary;
	
	public FunctionFactory(){
		myFunctionLibrary = new FunctionLibrary();
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
		return new Function(getName(type, str), terms);
	}
	
	public String getName(String type, String str){
		return type + " " + str;
	}
	
	public void setupSpecialConstants(){
		Constant gravity = new Constant("Gravity", -9.8);
		Constant pi = new Constant("Pi", Math.PI);
		myFunctionLibrary.addSpecialConstant(gravity);
		myFunctionLibrary.addSpecialConstant(pi);
	}
	
	public void setupDefaultTypes() {
		setupExpIncrFunction("Strong");
		setupExpIncrFunction("Moderate");
		setupExpIncrFunction("Weak");
	}
	
	public void setupDefaultStrengths(){
		Constant strong = new Constant(5,1);
		Constant moderate = new Constant(3, 1);
		Constant weak = new Constant(1.5, 1);
		myFunctionLibrary.addStrength("Strong", strong);
		myFunctionLibrary.addStrength("Moderate", moderate);
		myFunctionLibrary.addStrength("Weak", weak);
	}
	
	private void setupExpIncrFunction(String strength){
		List<Term> expIncrTerms = new ArrayList<>();
		List<Constant> expIncrConsts = new ArrayList<>();
		Constant const_proportionality = new Constant(1, 1);
		expIncrConsts.add(const_proportionality);
		List<Variable> expIncrVars1 = new ArrayList<>();
		expIncrVars1.add(new Variable("t", 1));
		List<Variable> expIncrVars2 = new ArrayList<>();
		expIncrVars2.add(new Variable("s", 1));
		Term expIncrTerm1 = new Term(expIncrVars1, expIncrConsts);
		Term expIncrTerm2 = new Term(expIncrVars2, expIncrConsts);
		expIncrTerms.add(expIncrTerm1);
		expIncrTerms.add(expIncrTerm2);
		String type = "Exponential Increase";
		myFunctionLibrary.addFunctionType(type, expIncrTerms);
		Function expIncr = new Function(getName(type, strength), expIncrTerms);
		myFunctionLibrary.addFunction(expIncr);
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