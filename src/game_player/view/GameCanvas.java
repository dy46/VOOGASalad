package game_player.view;

import java.util.ResourceBundle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class GameCanvas {
	
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    private ResourceBundle myResources;
    private Pane myRoot;
    private ScrollPane myScrollPane;
    private double paneWidth;
    private double paneHeight;

    public GameCanvas (ResourceBundle r) {
        myResources = r;
        myRoot = new Pane();
        myRoot.setPrefSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        paneWidth = CANVAS_HEIGHT;
        paneHeight = CANVAS_WIDTH;
    }

    public ScrollPane createCanvas () {
    	myScrollPane = new ScrollPane();
        myCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        myGC = myCanvas.getGraphicsContext2D();
        myGC.drawImage(new Image(myResources.getString("Background")), 0, 0);
        myRoot.getChildren().add(myCanvas);
        myScrollPane.setContent(myRoot);
        configureScrollPane(paneWidth, paneHeight);
        return myScrollPane;
    }

    public void updateNode () {
        // TODO Auto-generated method stub

    }
    
    public void configureScrollPane(double width, double length) {
    	myScrollPane.setPrefSize(width, length);
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
    
    public double getScrollPaneWidth() {
    	return myScrollPane.getWidth();
    }
    
    public double getScrollPaneHeight() {
    	return myScrollPane.getHeight();
    }
}
