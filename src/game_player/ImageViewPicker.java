package game_player;

import java.util.ResourceBundle;
import game_engine.game_elements.Unit;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewPicker {
    
    public static String EXTENSION = ".png";
    private ResourceBundle myBundle;
    private String name;
    private String currState;
    private int numFrames;
    private int currFrame;
    private ImageView imageView;
    private ClassLoader classLoader;
    private Group root;
    
    
    public ImageViewPicker(String name, int numFrames, String startingState, Group root) {
        this.root = root;
        this.name = name;
        this.numFrames = numFrames;
        this.currFrame = 0;
        this.currState = startingState;
        this.classLoader = getClass().getClassLoader();
        this.imageView = new ImageView();
        root.getChildren().add(imageView);
        imageView.toBack();
        myBundle = ResourceBundle.getBundle("game_engine/animation_rates/animation");
    }
    
    public void selectNextImageView(Unit u, int timer) {
        String state = u.getProperties().getState().getValue();
        if(timer % Integer.parseInt(myBundle.getString(name + state)) == 0) {
            currState = state;
            currFrame = currFrame + 1 == numFrames || !state.equals(currState) ? 1 : currFrame + 1;
            imageView.setImage(new Image(classLoader.getResourceAsStream(name + u.getProperties().getState().getValue() 
                                          + currFrame + EXTENSION)));
            imageView.setX(u.getProperties().getPosition().getX() - imageView.getImage().getWidth()/2);
            imageView.setY(u.getProperties().getPosition().getY() - imageView.getImage().getHeight()/2);
            imageView.setRotate(transformDirection(u));
            if(!u.isVisible()) {
                root.getChildren().remove(imageView);
            }
        }
    }
    
    public double transformDirection(Unit u) {
        return -u.getProperties().getVelocity().getDirection() + 90;
    }
    
}
