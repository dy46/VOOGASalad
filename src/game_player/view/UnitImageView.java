package game_player.view;

import game_engine.game_elements.Unit;
import javafx.scene.image.ImageView;

public class UnitImageView {
    
    private Unit myUnit;
    private ImageView imageView;
    
    public UnitImageView(Unit myUnit, String imageName) {
        this.myUnit = myUnit;
        this.imageView = new ImageView(imageName);
    }
    
    public Unit getUnit() {
        return myUnit;
    }
    
    public ImageView getImageView() {
        return imageView;
    }
    
   
}
