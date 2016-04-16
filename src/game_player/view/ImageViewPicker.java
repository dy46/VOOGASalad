package game_player.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import game_engine.CollisionDetector;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class ImageViewPicker {
    
    public static String EXTENSION = ".png";
    public final String[] leftCornerElements = {"Terrain"};
    public final String[] needsHealth = {"Enemy"};
    public final String[] seeRangeElements = {"Tower"};
    private ResourceBundle myBundle;
    private String name;
    private String currState;
    private int numFrames;
    private int currFrame;
    private ImageView imageView;
    private Pane root;
    private ImageView health;
    private Image healthImage;
    private Polygon range;
    private boolean rangeStart;
    
    
    public ImageViewPicker(String name, int numFrames, String startingState, Pane root) {
        this.root = root;
        this.name = name;
        this.numFrames = numFrames;
        this.currFrame = 0;
        this.currState = startingState;
        this.imageView = new ImageView();
        this.healthImage = new Image("health_red.png");
        this.health = new ImageView(healthImage);
        this.range = new Polygon();
        this.rangeStart = true;
        root.getChildren().add(range);
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
            boolean seeRange = Arrays.asList(seeRangeElements).contains(u.getClass().getSimpleName());
            if(seeRange && (timer % 60 == 0 || rangeStart)) {
                fillPolygonWithPoints(range, CollisionDetector.getUseableBounds(((Tower) u)
                                                                          .getMyProjectiles().get(0).getProperties().getRange(), 
                                                                           u.getProperties().getPosition()));
                range.setOpacity(0.1);
                range.toFront();
                rangeStart = false;
                
            }
            health.setX(xpos);
            health.setY(ypos);
            health.toFront();
            imageView.setRotate(transformDirection(u));
            if(!u.isVisible()) {
                root.getChildren().remove(range);
                root.getChildren().remove(imageView);
                root.getChildren().remove(health);
            }
        }
    }
    
    public Polygon fillPolygonWithPoints(Polygon polygon, List<Position> points) {
        for(Position p : points) {
            polygon.getPoints().addAll(new Double[]{p.getX(), p.getY()});
        }
        return polygon;
    }
    
    public double transformDirection(Unit u) {
        return -u.getProperties().getVelocity().getDirection() + 90;
    }
    
    public ImageView getImageView() {
        return imageView;
    }
    
}
