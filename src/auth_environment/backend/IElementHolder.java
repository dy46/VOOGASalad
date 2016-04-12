package auth_environment.backend;

import game_engine.game_elements.GameElement;

public interface IElementHolder {
	
	public void update(GameElement element);
	
	public GameElement unload(); 
}
