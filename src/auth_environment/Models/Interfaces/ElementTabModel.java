package auth_environment.Models.Interfaces;

import auth_environment.IAuthEnvironment;
import game_engine.factories.AffectorFactory;
import game_engine.factories.UnitFactory;

public class ElementTabModel implements IElementTabModel {
	
	private IAuthEnvironment myAuth;
	private UnitFactory myUnitFactory;
	private AffectorFactory myAffectorFactory; 
	
	public ElementTabModel(IAuthEnvironment auth) {
		this.myAuth = auth; 
		this.myUnitFactory = auth.getUnitFactory(); 
		this.myAffectorFactory = auth.getAffectorFactory();
		this.myUnitFactory.setAffectorLibrary(auth.getAffectorFactory().getAffectorLibrary());
		this.myAffectorFactory.setDefaultAffectors(auth.getFunctionFactory());
	}

	@Override
	public UnitFactory getUnitFactory() {
		return this.myUnitFactory;
	}

	@Override
	public AffectorFactory getAffectoryFactory() {
		return this.myAffectorFactory;
	}

}
