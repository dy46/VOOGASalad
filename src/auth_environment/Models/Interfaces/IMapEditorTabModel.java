package auth_environment.Models.Interfaces;

import java.util.List;

import auth_environment.IAuthEnvironment;
import game_engine.game_elements.Unit;

public interface IMapEditorTabModel {
	
	public void addTerrain(double xPos, double yPos, Unit element);

	public void deleteTerrain(Unit element);

	public List<Unit> getTerrains();
	
	public void refresh(IAuthEnvironment auth);
	
	public void convert(IMapPane mapPane);
	
	public void clear();

}
