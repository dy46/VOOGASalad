package auth_environment.backend;

import game_engine.game_elements.GameElement;

public interface IElementHolder {
	
	public void updateElement(GameElement element);
	
	public GameElement getElement(); 
}
