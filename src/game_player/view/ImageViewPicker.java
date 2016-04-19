package game_player.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.CollisionDetector;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class ImageViewPicker {
    
    public static String EXTENSION = ".png";
    public final String[] leftCornerElements = {"Terrain"};
    public final String[] needsHealth = {"Enemy", "Moab"};
    private ResourceBundle myBundle;
    private Unit myUnit;
    private String name;
    private String currState;
    private int numFrames;
    private int currFrame;
    private ImageView imageView;
    private Pane root;
    private ImageView health;
    private Image healthImage;
    
    public ImageViewPicker(Unit u, Pane root) {
        this.root = root;
        this.name = u.toString();
        this.numFrames = u.getNumFrames();
        this.currFrame = 0;
        this.myUnit = u;
        this.currState = u.getProperties().getState().getValue();
        this.imageView = new ImageView();
        this.healthImage = new Image("health_red.png");
        this.health = new ImageView(healthImage);
        root.getChildren().add(health);
        health.toBack();
        root.getChildren().add(imageView);
        myBundle = ResourceBundle.getBundle("game_engine/animation_rates/animation");
    }
    
    public void selectNextImageView(int timer) {
        String state = myUnit.getProperties().getState().getValue();
        if(timer % Integer.parseInt(myBundle.getString(name + state)) == 0) {
            currState = state;
            currFrame = currFrame + 1 == numFrames || !state.equals(currState) ? 1 : currFrame + 1;
            imageView.setImage(new Image(name + state + currFrame + EXTENSION));      
            boolean isCornerElement = myUnit.toString().contains(leftCornerElements[0]);
            double offsetX = isCornerElement ? 0 : -imageView.getImage().getWidth()/2;
            double offsetY = isCornerElement ? 0 : -imageView.getImage().getHeight()/2;
            imageView.setX(myUnit.getProperties().getPosition().getX() + offsetX);
            imageView.setY(myUnit.getProperties().getPosition().getY() + offsetY);
            boolean isHealth = myUnit.toString().contains(needsHealth[0]) ||  myUnit.toString().contains(needsHealth[1]);
            health.setFitWidth(myUnit.getProperties().getHealth().getValue()/myUnit.getProperties().getHealth().getInitialValue()*
                               healthImage.getWidth());
            double xpos = isHealth ? imageView.getX() : Integer.MAX_VALUE;
            double ypos = isHealth ? imageView.getY() - 5 : Integer.MAX_VALUE;
            health.setX(xpos);
            health.setY(ypos);
            health.toFront();
            imageView.setRotate(transformDirection(myUnit));
        }
    }
    
    public void removeElementsFromRoot() {
        root.getChildren().remove(imageView);
        root.getChildren().remove(health);
    }
    
    public double transformDirection(Unit u) {
        return -u.getProperties().getVelocity().getDirection() + 90;
    }
    
    public ImageView getImageView() {
        return imageView;
    }
    
    public Unit getUnit() {
        return myUnit;
    }
    
}
