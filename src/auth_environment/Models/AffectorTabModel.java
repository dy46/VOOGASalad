package auth_environment.Models;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IAffectorTabModel;
import game_engine.factories.AffectorFactory;

public class AffectorTabModel implements IAffectorTabModel {
	
	private AffectorFactory myAffectorFactory; 
	
	public AffectorTabModel(IAuthEnvironment auth) {
		this.myAffectorFactory = auth.getAffectorFactory();
		this.myAffectorFactory.setDefaultAffectors(auth.getFunctionFactory());
	}

	@Override
	public AffectorFactory getAffectorFactory() {
		return this.myAffectorFactory;
	}

}
