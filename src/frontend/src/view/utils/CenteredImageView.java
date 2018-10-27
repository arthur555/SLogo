package view.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CenteredImageView extends ImageView {
    public CenteredImageView(Image img) { super(img); }
    public void setCX(double x) {
        super.setX(x-getFitWidth()/2);
    }
    public void setCY(double y) {
        super.setY(y-getFitHeight()/2);
    }
    public double getCX() {
        return super.getX()+getFitWidth()/2;
    }
    public double getCY() {
        return super.getY()+getFitHeight()/2;
    }
}
