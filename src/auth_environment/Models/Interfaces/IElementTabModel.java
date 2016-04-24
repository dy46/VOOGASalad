package auth_environment.Models.Interfaces;

import game_engine.factories.AffectorFactory;
import game_engine.factories.UnitFactory;

public interface IElementTabModel {
	
	public UnitFactory getUnitFactory(); 
	
	public AffectorFactory getAffectoryFactory(); 
	
}
