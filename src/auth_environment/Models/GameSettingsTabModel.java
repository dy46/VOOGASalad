package auth_environment.Models;

import java.lang.reflect.Method;

//This entire file is part of my masterpiece.
//Brian Lin bl131	

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This class is the Model that corresponds to the Game Settings Tab View. 
 */

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import auth_environment.AuthEnvironment;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IGameSettingsTabModel;
import exceptions.WompException;
import game_data.IDataConverter;
import game_data.Serializer;
import game_engine.place_validations.PlaceValidation;
import game_engine.score_updates.ScoreUpdate;
import game_engine.wave_goals.WaveGoal;

public class GameSettingsTabModel implements IGameSettingsTabModel {
	
	private static final String SETTINGS_PACKAGE = "auth_environment/properties/gameSettings";
	private ResourceBundle mySettingsBundle = ResourceBundle.getBundle(SETTINGS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private IAuthModel myAuthModel;
	
	private IDataConverter<IAuthEnvironment> writer = new Serializer<IAuthEnvironment>();

	public GameSettingsTabModel(IAuthModel authModel) {
		myAuthModel = authModel;
	}

	@Override
	public void saveToFile() {
		writer.saveElement(myAuthModel.getIAuthEnvironment()); 
	}

	@Override
	public void loadFromFile() {
		myAuthModel.setIAuthEnvironment((AuthEnvironment) writer.loadElement()); 
	}

	@Override
	public void setGameName(String name) {
		myAuthModel.getIAuthEnvironment().setGameName(name);
	}

	@Override
	public String getGameName() {
		return myAuthModel.getIAuthEnvironment().getGameName();
	}
	
	@Override
	public void setSplashFile(String name) {
		myAuthModel.getIAuthEnvironment().setSplashScreen(name);
	}

	@Override
	public String getSplashFile() {
		return myAuthModel.getIAuthEnvironment().getSplashScreen();
	}
	
	@Override
	public List<String> getSelectedNames(String key) {
		return parseSettingsEntry(key + mySettingsBundle.getString("typeSufix")); 
	}

	private List<String> parseSettingsEntry(String key) {
		return Arrays.asList(mySettingsBundle.getString(key).split(mySettingsBundle.getString("delimiter")));
	}
	
	public void chooseItem(String selectedItem, String key) {
		try {
			Method method = myAuthModel.getIAuthEnvironment().getClass().getMethod(mySettingsBundle.getString("setPrefix") + key); 
			method.invoke( Class.forName(mySettingsBundle.getString(key + mySettingsBundle.getString("packageSuffix")) + selectedItem + key).getConstructor().newInstance()); 
		}
		catch (Exception e) {
			new WompException(mySettingsBundle.getString(key + mySettingsBundle.getString("errorSuffix"))).displayMessage();
		}
		
	}
}
