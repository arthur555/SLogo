package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import fake_model.TurtleModel;
import view.utils.BackgroundUtils;
import view.utils.ImageUtils;

import java.util.EventListener;

public class TurtleView {
    public static final int TURTLE_SIZE = 50;
    public static final int TURTLE_VIEW_WIDTH = MainView.SCREEN_HEIGHT;

    private int x;
    private int y;
    private boolean penDown;
    private GridPane root;
    private ImageView turtle;
    private Color penColor;

    public TurtleView(TurtleModel turtleModel) {
        turtle = new ImageView();
        root = new GridPane();
        root.getStyleClass().add("canvas");
        root.setPrefWidth(200);
        root.setPrefHeight(200);


        turtle.rotateProperty().bind(turtleModel.getAnlge());
        turtle.visibleProperty().bind(turtleModel.isVisible());

        turtleModel.getPoints().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                System.out.println(c.getList().get(0));
                System.out.println(c.getList().get(1));
            }
        });



        //

    }

    public Pane view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(int x, int y, Image v) {
        System.out.println(x + " " + y);
        turtle.setImage(v);
        turtle.setX(x);
        turtle.setY(y);
        turtle.setRotate(0);
    }
    public void setPenColor(Color c) {penColor = c;}
    public void toWards(Point2D pos) { }
    public void forWards(int distance){ }

    String command;




}
