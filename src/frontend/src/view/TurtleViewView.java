package view;

import view.api.TurtleViewAPI;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TurtleModel;
import utils.BackgroundUtils;

public class TurtleViewView implements TurtleViewAPI {
    private Pane root;

    public TurtleViewView(TurtleModel turtleModel) {
        root = new Pane();
        root.getStyleClass().add("canvas");
        root.getChildren().add(new Rectangle(200, 200, 200, 200));
    }

    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(int idx, ImageView v) { }
    public void setPenColor(int idx, Color c) { }
    public void moveTurtlePositionAndAngle(int idx, Point2D pos, double angle) { }
    Node getView() { return root; }
}
