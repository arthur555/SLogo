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
import javafx.util.Duration;
import model.ClearListener;
import model.TurtleModel;
import view.utils.ImageUtils;

/**
 *  Takes care of one single Turtle
 */
public class TurtleView implements ClearListener {
    public static final int TURTLE_SIZE = 50;

    private ObservableList<Node> views;
    private BooleanProperty penDown;
    private ImageView turtle;
    private Image turtleImg;
    private Color penColor;
    private DoubleProperty duration;

    public TurtleView(TurtleModel turtleModel, DoubleProperty durationModel) {
        views = FXCollections.observableArrayList();

        turtleImg = ImageUtils.getImageFromUrl("turtle_1.png", TURTLE_SIZE, TURTLE_SIZE);
        turtle = new ImageView(turtleImg);
        turtle.setX(turtleModel.getX());
        turtle.setY(turtleModel.getY());
        turtle.setRotate(-turtleModel.getAngle());
        turtle.visibleProperty().bind(turtleModel.isVisibleModel());
        views.add(turtle);

        penColor = Color.BLACK;
        penDown = new SimpleBooleanProperty();
        penDown.bind(turtleModel.isPenDownModel());

        duration = new SimpleDoubleProperty();
        duration.bind(durationModel);

        bindObservable(turtleModel);
    }

    private void bindObservable(TurtleModel turtleModel) {
        turtleModel.posAndAngleModel().registerListener((newValue) -> {
            var newX = newValue.x();
            var newY = newValue.y();
            var newAngle = newValue.angle();

            var path = makePath(newX, newY, duration.doubleValue());
            var animation = makeAnimation(turtle, path, newAngle);
            var capturedPenDown = penDown.getValue();
            animation.currentTimeProperty().addListener((e, o, n) -> {
                if(n.toMillis() > duration.doubleValue()) return;
                if(capturedPenDown) { views.add(makePath(newX, newY, n.toMillis())); }
            });
            animation.setOnFinished(e -> {
                views.remove(turtle);
                turtle = new ImageView(turtleImg);
                turtle.setRotate(newAngle);
                turtle.setX(newX);
                turtle.setY(newY);
                turtle.visibleProperty().bind(turtleModel.isVisibleModel());
                views.add(turtle);
            });
            animation.play();
        });
    }

    private Path makePath(double newX, double newY, double o) {
        var path = new Path();
        if(o == duration.doubleValue())
            path.setFill(penColor);
        path.getElements().add(new MoveTo(turtle.getX()+TURTLE_SIZE/2,turtle.getY()+TURTLE_SIZE/2));
        path.getElements().add(new LineTo(
                turtle.getX()+TURTLE_SIZE/2+o/duration.doubleValue() *(newX-turtle.getX()),
                turtle.getY()+TURTLE_SIZE/2+o/duration.doubleValue() *(newY-turtle.getY())));
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

    public ObservableList<Node> views() { return views; }
    public void setTurtleImage(Image v) { turtleImg = v; turtle.setImage(v); }
    public void setPenColor(Color c) { penColor = c; }

    @Override
    public void clear() {
        views.clear();
        views.add(turtle);
    }
}
