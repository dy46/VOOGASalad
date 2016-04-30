package auth_environment.Models;
import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.IAuthModel;
import game_engine.factories.StoreFactory;

public class StoreTabModel {
	
	private IAuthModel myAuthModel;
	private IAuthEnvironment myAuthEnvironment; 
	private StoreFactory myStoreFactory;
	
	public StoreTabModel(IAuthModel authModel) {
		this.myAuthModel = authModel; 
		this.myAuthEnvironment = authModel.getIAuthEnvironment();
		this.myStoreFactory = this.myAuthEnvironment.getStoreFactory();
	}
	
	

}
