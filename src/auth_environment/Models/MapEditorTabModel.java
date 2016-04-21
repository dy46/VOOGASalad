package auth_environment.Models;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IMapEditorTabModel;
import game_data.AuthSerializer;

public class MapEditorTabModel implements IMapEditorTabModel{

	private IAuthEnvironment myAuthData;  
	
	// TODO: are type arguments necessary? 
	private AuthSerializer writer = new AuthSerializer();

	public MapEditorTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;  
	}

	@Override
	public void saveToFile() {
		writer.saveElement(this.myAuthData); 
	}

	@Override
	public void loadFromFile() {
		// TODO: add error checking
		this.myAuthData = (IAuthEnvironment) writer.loadElement();
	}

	@Override
	public void setGameName(String name) {
		this.myAuthData.setGameName(name);
	}

	@Override
	public String getGameName() {
		return this.myAuthData.getGameName();
	}

	@Override
	public void setSplashFile(String name) {
		this.myAuthData.setSplashScreen(name);
	}

	@Override
	public String getSplashFile() {
		return this.myAuthData.getSplashScreen();
	}

}
