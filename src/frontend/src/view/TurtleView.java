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
import model.TurtleModel;
import view.utils.BackgroundUtils;
import view.utils.ImageUtils;

public class TurtleView {
    public static final int TURTLE_SIZE = 50;
    public static final int TURTLE_VIEW_WIDTH = MainView.SCREEN_HEIGHT;

    private Image turtleImg;
    private boolean penDown;
    private boolean move;
    private Pane root;
    private ImageView turtle;
    private Color penColor;
    private int duration = 100;

    public TurtleView(TurtleModel turtleModel) {
        turtleImg = ImageUtils.getImageFromUrl("turtle_image_button.png", TURTLE_SIZE, TURTLE_SIZE);
        turtle = new ImageView(turtleImg);
        turtle.setX(turtleModel.getX());
        turtle.setY(turtleModel.getY());
        turtle.setRotate(-turtleModel.getAngle());
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

    private void bindObservable(TurtleModel turtleModel) {
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
                var newY = -c.getList().get(1);
                var newAngle = -c.getList().get(2);
                if (move){
                    var path = makePath(newX, newY, (double) duration);
                    var animation = makeAnimation(turtle, path, newAngle);
                    var capturedPenDown = penDown;
                    animation.currentTimeProperty().addListener((e, o, n) -> {
                        if(n.toMillis() > duration) return;
                        if(capturedPenDown) { root.getChildren().add(makePath(newX, newY, n.toMillis())); }
                    });
                    animation.setOnFinished(e -> {
                        root.getChildren().remove(turtle);
                        turtle = new ImageView(turtleImg);
                        turtle.setRotate(newAngle);
                        turtle.setX(newX);
                        turtle.setY(newY);
                        turtle.visibleProperty().bind(turtleModel.isVisible());
                        root.getChildren().add(turtle);
                    });
                    animation.play();
                }
            }
        });
    }

    public void clean(){
        root.getChildren().clear();
        root.getChildren().add(turtle);
    }


    private Path makePath(double newX, double newY, double o) {
        var path = new Path();
        if(o == duration)
            path.setFill(penColor);
        path.getElements().add(new MoveTo(turtle.getX()+TURTLE_SIZE/2,turtle.getY()+TURTLE_SIZE/2));
        path.getElements().add(new LineTo(
                turtle.getX()+TURTLE_SIZE/2+o/(double) duration *(newX-turtle.getX()),
                turtle.getY()+TURTLE_SIZE/2+o/(double) duration *(newY-turtle.getY())));
        path.setStroke(penColor);
        return path;
    }

    private Animation makeAnimation(ImageView turtle, Path path, Double newAngle){
        var pt = new PathTransition(Duration.millis(duration),path,turtle);
        var rt = new RotateTransition(Duration.millis(duration));
        rt.setToAngle(newAngle);
        pt.setDuration(Duration.millis(duration));
        rt.setDuration(Duration.millis(duration));

        if(newAngle == turtle.getRotate()) {
            return new SequentialTransition(turtle,pt);
        } else {
            return new SequentialTransition(turtle,rt);
        }
    }

    public Pane view() { return root; }
    public void setBackgroundColor(Color c) { root.setBackground(BackgroundUtils.coloredBackground(c)); }
    public void setTurtleImage(Image v) { turtle.setImage(v); }
    public void setPenColor(Color c) {penColor = c;}
    public void setDuration(double duration) { this.duration = (int) (duration*1000); }
}
