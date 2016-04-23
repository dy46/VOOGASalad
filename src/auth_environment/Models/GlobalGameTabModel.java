package auth_environment.Models;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.IGlobalGameTabModel;
import game_data.Serializer;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

public class GlobalGameTabModel implements IGlobalGameTabModel {
	
	private IAuthModel myAuthModel;
	
	private Serializer<IAuthEnvironment> writer = new Serializer<IAuthEnvironment>();

	public GlobalGameTabModel(IAuthModel authModel) {
		this.myAuthModel = authModel;
	}

	@Override
	public void saveToFile() {
		writer.saveElement(this.myAuthModel.getIAuthEnvironment()); 
	}

	@Override
	public void loadFromFile() {
		// TODO: add error checking
		this.myAuthModel.setIAuthEnvironment((IAuthEnvironment) writer.loadElement());
	}

	@Override
	public void setGameName(String name) {
		this.myAuthModel.getIAuthEnvironment().setGameName(name);
	}

	@Override
	public String getGameName() {
		return this.myAuthModel.getIAuthEnvironment().getGameName();
	}

	@Override
	public void setSplashFile(String name) {
		this.myAuthModel.getIAuthEnvironment().setSplashScreen(name);
	}

	@Override
	public String getSplashFile() {
		return this.myAuthModel.getIAuthEnvironment().getSplashScreen();
	}
}
