package auth_environment.delegatesAndFactories;

import java.util.List;

import auth_environment.view.Tile;
import game_engine.game_elements.GameElement;

public class LevelBuilderDelegate implements ITileDelegate {
	
	private List<GameElement> myElements; 
	private List<Tile> myTiles; 
	
	public LevelBuilderDelegate(List<GameElement> elements) {
		this.myElements = elements; 
	}

	@Override
	public void importTiles(List<Tile> tiles) {
		this.myTiles = tiles; 
	}

}
