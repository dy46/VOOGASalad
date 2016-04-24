package auth_environment.Models;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAnimationTabModel;
import auth_environment.backend.StateImageSaver;
import game_engine.game_elements.Unit;

public class AnimationTabModel implements IAnimationTabModel {
	
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private Unit myUnit;

	private List<File> myFiles; 
	
	private StateImageSaver mySaver;
	
	public AnimationTabModel(Unit unit) {
		this.mySaver = new StateImageSaver(this.myURLSBundle.getString("saveImageLocation")); 
	}

	@Override
	public void addFile(File file) {
		this.myFiles.add(file);
	}
}
