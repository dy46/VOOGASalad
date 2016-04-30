package auth_environment.Models;

import auth_environment.AuthEnvironment;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IAuthModel;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This class holds the highest level of Auth Environment backend data. Most important is a single instance
 * of IEngineWorkspace.java (all of our data). 
 * 
 * There will also be some helper methods- such as saving or loading the data. 
 */

public class AuthModel implements IAuthModel {
	
	private IAuthEnvironment authInterface; 
	
	public AuthModel() {
		// Start with empty EngineWorkspace. Can load one from file however. 
		authInterface = new AuthEnvironment(); 
	}

	@Override
	public IAuthEnvironment getIAuthEnvironment() {
		return this.authInterface;
	}

	@Override
	public void setIAuthEnvironment(IAuthEnvironment auth) {
		this.authInterface = auth;
	}

}
