package auth_environment.buildingBlocks;

public class TerrainBuildingBlock extends BuildingBlock{
	
	private String myProperty;
	private String myEffect; 
	
	
	public TerrainBuildingBlock(){
	}
	
	public String getMyProperty() {
		return myProperty;
	}


	public void setMyProperty(String myProperty) {
		this.myProperty = myProperty;
	}


	public String getMyEffect() {
		return myEffect;
	}


	public void setMyEffect(String myEffect) {
		this.myEffect = myEffect;
	}
	
	
}
