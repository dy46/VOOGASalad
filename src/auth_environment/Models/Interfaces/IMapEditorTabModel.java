package auth_environment.Models.Interfaces;

import java.util.List;

import game_engine.game_elements.Unit;

public interface IMapEditorTabModel {
	
	public void addTerrain(double xPos, double yPos, Unit element);

	public void deleteTerrain(double xPos, double yPos);

	public void deleteTerrain(Unit element);

	public void updateTerrainList(List<Unit> update);

	public List<Unit> getTerrains();

}
