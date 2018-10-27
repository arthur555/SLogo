package view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.ClearListener;
import model.TurtleModel;
import view.utils.CenteredImageView;
import view.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Queue;

/**
 *  Takes care of one single Turtle
 */
public class TurtleView implements ClearListener {
    public static final int TURTLE_SIZE = 50;
    public static final int SCREEN_SIZE = 800;

    private ObservableList<Path> paths;
    private BooleanProperty penDown;
    private CenteredImageView turtle;
    private Image turtleImg;
    private Color penColor;
    private DoubleProperty duration;
    private Animation currentAnimation;
    private Queue<Animation> animationQueue;
    private Queue<Path> pathQueue;
    private Queue<Boolean> pendownQueue;

    public TurtleView(TurtleModel turtleModel, DoubleProperty durationModel) {
        paths = FXCollections.observableArrayList();
        turtleImg = ImageUtils.getImageFromUrl("turtle_1.png", TURTLE_SIZE, TURTLE_SIZE);
        turtle = new CenteredImageView(turtleImg);
        turtle.setCX(turtleModel.getX());
        turtle.setCY(turtleModel.getY());
        turtle.setRotate(-turtleModel.getAngle());
        turtle.visibleProperty().bind(turtleModel.isVisibleModel());

        penColor = Color.BLACK;
        penDown = new SimpleBooleanProperty();
        penDown.bind(turtleModel.isPenDownModel());

        duration = new SimpleDoubleProperty();
        duration.bind(durationModel);

        bindObservable(turtleModel);
    }

    private void bindObservable(TurtleModel turtleModel) {
        turtleModel.posAndAngleModel().registerListener((newValue) -> {
            var newX = newValue.x()%SCREEN_SIZE;
            var newY = newValue.y()%SCREEN_SIZE;
            var newAngle = newValue.angle();

            var path = makePath(newX, newY, duration.doubleValue());
            var animation = makeAnimation(turtle, path, newAngle);
            var capturedPenDown = penDown.getValue();

            animation.currentTimeProperty().addListener((e, o, n) -> {
                if(n.toMillis() > duration.doubleValue()) return;
                if(capturedPenDown) {
                    paths.clear();
                    paths.add(makePath(newX, newY, n.toMillis()));
                }
            });
            animation.setOnFinished(e -> {
                turtle = new CenteredImageView(turtleImg);
                turtle.setRotate(newAngle);
                turtle.setCX(newX);
                turtle.setCY(newY);
                turtle.visibleProperty().bind(turtleModel.isVisibleModel());
            });
            animation.play();
        });
    }

    private Path makePath(double newX, double newY, double o) {
        var path = new Path();
        if(o == duration.doubleValue())
            path.setFill(penColor);
        path.getElements().add(new MoveTo(turtle.getCX(),turtle.getCY()));
        path.getElements().add(new LineTo(
                turtle.getCX()+o/duration.doubleValue()*(newX-turtle.getCX()),
                turtle.getCY()+o/duration.doubleValue()*(newY-turtle.getCY())));
        path.setStroke(penColor);
        return path;
    }

    private Animation makeAnimation(ImageView turtle, Path path, Double newAngle){
        var pt = new PathTransition(Duration.millis(duration.doubleValue()),path,turtle);
        var rt = new RotateTransition(Duration.millis(duration.doubleValue()));
        rt.setToAngle(newAngle);
        pt.setDuration(Duration.millis(duration.doubleValue()));
        rt.setDuration(Duration.millis(duration.doubleValue()));

        if(newAngle == turtle.getRotate()) {
            return new SequentialTransition(turtle,pt);
        } else {
            return new SequentialTransition(turtle,rt);
        }
    }

    private void applyViewport(Rectangle viewport) {
        for(var view : views) {

        }
    }

    public ObservableList<Node> views() { return views; }
    public void setTurtleImage(Image v) { turtleImg = v; turtle.setImage(v); }
    public void setPenColor(Color c) { penColor = c; }

    @Override
    public void clear() {
        paths.clear();
    }
}
