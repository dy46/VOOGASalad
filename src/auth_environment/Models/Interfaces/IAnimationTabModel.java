package auth_environment.Models.Interfaces;

import java.io.File;
import java.io.IOException;

import game_engine.game_elements.Unit;

public interface IAnimationTabModel {
	
	public void addFile(File file);
	
	public void saveFiles();
	
	public void setUnit(Unit unit);
	
}
