package game_player.view;

import java.util.ResourceBundle;
import game_player.GameDataSource;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GameCanvas {

    private static final int CANVAS_LENGTH = 500;
    private static final int CANVAS_WIDTH = 500;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private ResourceBundle myResources;
    private Pane myRoot;
    private ScrollPane myScrollPane;

    public GameCanvas (ResourceBundle r) {
        myResources = r;
        myRoot = new Pane();
        myRoot.setPrefSize(CANVAS_WIDTH, CANVAS_LENGTH);
    }

    public ScrollPane createCanvas () {
    	myScrollPane = new ScrollPane();
        myCanvas = new Canvas(CANVAS_LENGTH, CANVAS_WIDTH);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.drawImage(new Image("background.png"), 0, 0);
        myRoot.getChildren().add(myCanvas);
        myScrollPane.setContent(myRoot);
        configureScrollPane();
        return myScrollPane;
    }

    public void updateNode () {
        // TODO Auto-generated method stub

    }
    
    private void configureScrollPane() {
    	myScrollPane.setPrefSize(500, 500);
    	myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	myScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    	myScrollPane.setPannable(true);
    }

    public Canvas getCanvas () {
        return myCanvas;
    }

    public Pane getRoot () {
        return myRoot;
    }
}
