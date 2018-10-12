package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TurtleModel;
import view.utils.BackgroundUtils;

public class TurtleView {
    public static int TURTLE_VIEW_WIDTH = ViewModule.SCREEN_HEIGHT;

    private Pane root;

    public TurtleView(TurtleModel turtleModel) {
        root = new Pane();
        root.getStyleClass().add("canvas");
        root.getChildren().add(new Rectangle(200, 200, 200, 200));
    }

    public Node view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(int idx, ImageView v) { }
    public void setPenColor(int idx, Color c) { }
    public void moveTurtlePositionAndAngle(int idx, Point2D pos, double angle) { }
}
