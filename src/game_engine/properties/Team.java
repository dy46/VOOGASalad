package game_engine.properties;

public class Team {

	private String myTeam;
	
	public Team(String team){
		this.myTeam = team;
	}
	
	public String getTeam(){
		return myTeam;
	}
	
	public void setTeam(String team){
		this.myTeam = team;
	}
	
}