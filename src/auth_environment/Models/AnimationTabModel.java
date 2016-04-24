package auth_environment.Models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.Models.Interfaces.IAnimationTabModel;
import auth_environment.delegatesAndFactories.StateImageSaver;
import game_engine.game_elements.Unit;

public class AnimationTabModel implements IAnimationTabModel {
	private static final String SUFFIX = ".png";
	private static final String URLS_PACKAGE = "auth_environment/properties/urls";
	private ResourceBundle myURLSBundle = ResourceBundle.getBundle(URLS_PACKAGE);
	
	private Unit myUnit;

	private List<File> myFiles; 
	
	private StateImageSaver mySaver;
	
	public AnimationTabModel(Unit unit) {
		this.mySaver = new StateImageSaver(this.myURLSBundle.getString("saveImageLocation"), SUFFIX); 
		this.myFiles = new ArrayList<File>();
		this.myUnit = unit;
	}

	@Override
	public void addFile(File file) {
		this.myFiles.add(file);
	}
	
	public void saveFiles(){
		System.out.println(this.myUnit.getType());
		System.out.println(this.myUnit.getName());
		this.mySaver.saveFiles(this.myUnit.getType(), this.myUnit.getName(), this.myFiles);
	}
}
