package auth_environment.Models.Interfaces;

import auth_environment.IAuthEnvironment;
import game_engine.factories.UnitFactory;

public class ElementTabModel implements IElementTabModel {
	
	private IAuthEnvironment myAuth;
	private UnitFactory myUnitFactory;
	
	public ElementTabModel(IAuthEnvironment auth) {
		this.myAuth = auth; 
		this.myUnitFactory = auth.getUnitFactory(); 
	}

	@Override
	public UnitFactory getUnitFactory() {
		return this.myUnitFactory;
	}

}
