package view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import model.TurtleModelImpl;
import view.utils.BackgroundUtils;
import view.utils.ImageUtils;

public class TurtleView {
    public static final int TURTLE_SIZE = 50;
    public static final int TURTLE_VIEW_WIDTH = MainView.SCREEN_HEIGHT;
    public static final int DURATION_MILLIS = 4000;
    private Image turtleImg;
    private boolean penDown;
    private boolean move;
    private double angle;
    private Pane root;
    private ImageView turtle;
    private Color penColor;

    public TurtleView(TurtleModelImpl turtleModel) {
        turtleImg = ImageUtils.getImageFromUrl("turtle_image_button.png", TURTLE_SIZE, TURTLE_SIZE);
        turtle = new ImageView(turtleImg);
        turtle.setX(100);
        turtle.setY(100);
        root = new Pane();
        root.getStyleClass().add("canvas");
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(turtle);
        penColor = Color.BLACK;

        penDown = true;
        turtle.visibleProperty().bind(turtleModel.isVisible());

        bindObservable(turtleModel);
    }

    private void bindObservable(TurtleModelImpl turtleModel) {
        turtleModel.isMove().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                move = newValue;
            }
        });
        turtleModel.isPenDown().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                penDown = newValue;
            }
        });

        turtleModel.getClean().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                clean();
            }
        });

        turtleModel.getPoints().addListener(new ListChangeListener<Double>() {
            @Override
            public void onChanged(Change<? extends Double> c) {
                var newX = c.getList().get(0);
                var newY = c.getList().get(1);
                if (move){
                    var path = makePath(newX, newY);
                    var animation = makeAnimation(turtle, path);
//                    animation.currentTimeProperty().addListener((e, o, n) -> {
//                        if(!o.equals(Duration.ZERO)) root.getChildren().remove(root.getChildren().size()-1);
//                        root.getChildren().add(makePath(newX, newY));
//                    });
                    animation.play();
                }
            }
        });
    }

    public void clean(){
        root.getChildren().removeAll();
        root.getChildren().add(turtle);
    }


    private Path makePath(double newX, double newY) {
        var path = new Path();
        path.setFill(penColor);
        path.getElements().add(new MoveTo(turtle.getX()+TURTLE_SIZE/2,turtle.getY()+TURTLE_SIZE/2));
        path.getElements().add(new LineTo(newX, newY));
        path.setStroke(penColor);
        return path;
    }

    private Animation makeAnimation(ImageView turtle, Path path){
        if(penDown) { }
        var pt = new PathTransition(Duration.millis(DURATION_MILLIS),path,turtle);
        var rt = new RotateTransition(Duration.millis(DURATION_MILLIS));
        rt.setToAngle(90);
        pt.setDuration(Duration.millis(1000)); //Todo:
        rt.setDuration(Duration.millis(1000));
        return new SequentialTransition(turtle,pt,rt);
    }

    public Pane view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(Image v) {
        turtle.setImage(v);
        turtle.setRotate(0);
    }
    public void setPenColor(Color c) {penColor = c;}
}
