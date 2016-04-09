package game_player.view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas implements IGUIObject {
	
	private static final Color DEFAULT_COLOR = Color.AQUAMARINE;
	
	private static final int CANVAS_LENGTH = 600;
	private static final int CANVAS_WIDTH = 500;

	private Canvas myCanvas;
	private GraphicsContext myGC;
	private ResourceBundle myResources;
	
	public GameCanvas(ResourceBundle r) {
		myResources = r;
	}
	
	@Override
	public Node createNode() {
		myCanvas = new Canvas(CANVAS_LENGTH, CANVAS_WIDTH);
		myGC = myCanvas.getGraphicsContext2D();
		myGC.setFill(DEFAULT_COLOR);
		myGC.fillRect(0, 0, CANVAS_LENGTH, CANVAS_WIDTH);
		return myCanvas;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub

	}

}
