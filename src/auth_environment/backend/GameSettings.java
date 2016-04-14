package auth_environment.backend;

public class GameSettings implements ISettings {
	
	private String myName;
	
	public GameSettings() {
		
	}

	@Override
	public void setName(String name) {
		this.myName = name;
	}

	@Override
	public String getName() {
		return this.myName;
	}

}
