package game_player.view;

import java.util.Arrays;
import java.util.ResourceBundle;
import game_engine.game_elements.Unit;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageViewPicker {
    
    public static String EXTENSION = ".png";
    public final String[] leftCornerElements = {"Terrain"};
    public final String[] needsHealth = {"Enemy"};
    private ResourceBundle myBundle;
    private String name;
    private String currState;
    private int numFrames;
    private int currFrame;
    private ImageView imageView;
    private Pane root;
    private ImageView health;
    private Image healthImage;
    
    
    public ImageViewPicker(String name, int numFrames, String startingState, Pane root) {
        this.root = root;
        this.name = name;
        this.numFrames = numFrames;
        this.currFrame = 0;
        this.currState = startingState;
        this.imageView = new ImageView();
        this.healthImage = new Image("health_red.png");
        this.health = new ImageView(healthImage);
        root.getChildren().add(health);
        root.getChildren().add(imageView);
        myBundle = ResourceBundle.getBundle("game_engine/animation_rates/animation");
    }
    
    public void selectNextImageView(Unit u, int timer) {
        String state = u.getProperties().getState().getValue();
        if(timer % Integer.parseInt(myBundle.getString(name + state)) == 0) {
            currState = state;
            currFrame = currFrame + 1 == numFrames || !state.equals(currState) ? 1 : currFrame + 1;
            imageView.setImage(new Image(name + u.getProperties().getState().getValue() 
                                          + currFrame + EXTENSION));      
            boolean isCornerElement = Arrays.asList(leftCornerElements).contains(u.getClass().getSimpleName());
            double offsetX = isCornerElement ? 0 : -imageView.getImage().getWidth()/2;
            double offsetY = isCornerElement ? 0 : -imageView.getImage().getHeight()/2;
            imageView.setX(u.getProperties().getPosition().getX() + offsetX);
            imageView.setY(u.getProperties().getPosition().getY() + offsetY);
            boolean isHealth = Arrays.asList(needsHealth).contains(u.getClass().getSimpleName());   
            health.setFitWidth(u.getProperties().getHealth().getValue()/u.getProperties().getHealth().getInitialValue()*
                               healthImage.getWidth());
            double xpos = isHealth ? imageView.getX() : Integer.MAX_VALUE;
            double ypos = isHealth ? imageView.getY() - 5 : Integer.MAX_VALUE;
            health.setX(xpos);
            health.setY(ypos);
            health.toFront();
            imageView.setRotate(transformDirection(u));
            if(!u.isVisible()) {
                root.getChildren().remove(imageView);
                root.getChildren().remove(health);
            }
        }
    }
    
    public double transformDirection(Unit u) {
        return -u.getProperties().getVelocity().getDirection() + 90;
    }
    
}
