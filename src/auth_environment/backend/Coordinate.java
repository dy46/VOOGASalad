package auth_environment.backend;

import game_engine.game_elements.GameElement;
import javafx.scene.image.Image;

// TODO: deprecate this class (replace with Game Engine's Position.java class) 

public class Coordinate{
	
	private int x;
	private int y;
	GameElement element;
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setElement(GameElement element){
		this.element = element;
	}
	
	public GameElement getElement(){
		return this.element;
	}
	
}
