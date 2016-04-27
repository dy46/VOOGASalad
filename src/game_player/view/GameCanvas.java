package game_player.view;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GameCanvas {

    private static final Color DEFAULT_COLOR = Color.AQUAMARINE;

    private static final int CANVAS_LENGTH = 500;
    private static final int CANVAS_WIDTH = 500;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private ResourceBundle myResources;
    private Pane myRoot;

    public GameCanvas (ResourceBundle r) {
        myResources = r;
        myRoot = new Pane();
        myRoot.setPrefSize(CANVAS_WIDTH, CANVAS_LENGTH);
    }

    public Pane createCanvas () {
        myCanvas = new Canvas(CANVAS_LENGTH, CANVAS_WIDTH);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.drawImage(new Image("background.png"), 0, 0);
        myRoot.getChildren().add(myCanvas);
        return myRoot;
    }

    public void updateNode () {
        // TODO Auto-generated method stub

    }

    public Canvas getCanvas () {
        return myCanvas;
    }

    public Pane getRoot () {
        return myRoot;
    }
}
