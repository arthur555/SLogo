package view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.TurtleModel;
import view.utils.BackgroundUtils;
import view.utils.ImageUtils;

public class TurtleView {
    public static final int TURTLE_SIZE = 50;
    public static final int TURTLE_VIEW_WIDTH = MainView.SCREEN_HEIGHT;

    private static final Image DEFAULT_TURTLE_IMG =
            ImageUtils.getImageFromUrl("turtle_image_button.png", TURTLE_SIZE, TURTLE_SIZE);


    private GridPane root;
    private ImageView turtle;
    private Color penColor;

    TurtleView(TurtleModel turtleModel) {
        root = new GridPane();
        root.getStyleClass().add("canvas");
        root.setPrefWidth(TURTLE_VIEW_WIDTH);
        root.setPrefHeight(TURTLE_VIEW_WIDTH);
        turtle = new ImageView();
        setTurtleImage(TURTLE_VIEW_WIDTH/2, TURTLE_VIEW_WIDTH/2, DEFAULT_TURTLE_IMG);
        root.getChildren().add(turtle);
        //root.getChildren().add(new Rectangle(200, 200, 200, 200));
    }

    public Pane view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(int x, int y, Image v) {
        turtle.setImage(v);
        turtle.setX(x);
        turtle.setY(y);
        turtle.setRotate(0);
//        root.add(turtle, x, y);
    }
    public void setPenColor(Color c) {penColor = c;}
    public void toWards(Point2D pos) { }
}
