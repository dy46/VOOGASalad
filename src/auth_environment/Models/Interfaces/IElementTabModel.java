package auth_environment.Models.Interfaces;

import auth_environment.IAuthEnvironment;
import game_engine.factories.AffectorFactory;
import game_engine.factories.UnitFactory;

public interface IElementTabModel {
	
	public UnitFactory getUnitFactory(); 
	
	public AffectorFactory getAffectoryFactory();

	public void update(IAuthEnvironment iAuthEnvironment); 
	
}
