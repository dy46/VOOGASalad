package auth_environment.Models;

import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAnimationLoaderTabModel;
import auth_environment.backend.StateImageSaver;

public class AnimationLoaderTabModel implements IAnimationLoaderTabModel {
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private StateImageSaver mySaver;
	
	public AnimationLoaderTabModel() {
		this.mySaver = new StateImageSaver(this.myURLSBundle.getString("saveImageLocation")); 
	}
	
	
}
