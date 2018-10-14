package view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TurtleModel;
import view.utils.BackgroundUtils;

public class TurtleView {
    public static final String turtleImage = "turtle_image_button.png";
    static int TURTLE_VIEW_WIDTH = MainView.SCREEN_HEIGHT;

    private GridPane root;
    private ImageView turtle;
    private Color penColor;

    TurtleView(TurtleModel turtleModel) {
        root = new GridPane();
        root.getStyleClass().add("canvas");
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        //root.getChildren().add(new Rectangle(200, 200, 200, 200));
    }

    public Pane view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(int x, int y, ImageView v) {
        turtle.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(turtleImage)));
        turtle.setX(x);
        turtle.setY(y);
        turtle.setRotate(0);
        root.add(turtle, x, y);
    }
    public void setPenColor(Color c) {penColor = c;}
    public void toWards(Point2D pos) { }
}
