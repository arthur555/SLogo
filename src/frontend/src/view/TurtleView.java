package view;

import javafx.animation.Animation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import model.ClearListener;
import model.TurtleModel;
import view.utils.AnimationQueue;
import view.utils.ImageUtils;

/**
 *  Takes care of one single Turtle
 */
public class TurtleView implements ClearListener {
    public static final int TURTLE_SIZE = 50;
    public static final int ANIMATION_LIMIT = 200;

    private Group views;
    private BooleanProperty penDown;
    private ImageView turtle;
    private Image turtleImg;
    private Color penColor;
    private DoubleProperty duration;
    private AnimationQueue animationQueue;
    private double tempX;
    private double tempY;
    private TurtleModel model;

    public TurtleView(TurtleModel turtleModel, DoubleProperty durationModel) {
        views = new Group();
        turtleImg = ImageUtils.getImageFromUrl("turtle_1.png", TURTLE_SIZE, TURTLE_SIZE);
        turtle = new ImageView(turtleImg);
        turtle.setX(turtleModel.getX());
        turtle.setY(turtleModel.getY());
        tempX = turtle.getX()+ TURTLE_SIZE/2;
        tempY = turtle.getY()+ TURTLE_SIZE/2;
        turtle.setRotate(-turtleModel.getAngle());
        turtle.visibleProperty().bind(turtleModel.isVisibleModel());
        views.getChildren().add(turtle);

        penColor = Color.BLACK;
        penDown = new SimpleBooleanProperty();
        penDown.bind(turtleModel.isPenDownModel());

        duration = new SimpleDoubleProperty();
        duration.bind(durationModel);
        animationQueue = new AnimationQueue(ANIMATION_LIMIT);
        bindObservable(turtleModel);

        model = turtleModel;
    }

    private void bindObservable(TurtleModel turtleModel) {
        turtleModel.posAndAngleModel().registerListener((newValue) -> {
            var newX = newValue.x();
            var newY = newValue.y();
            var newAngle = newValue.angle();

            var path = makePath(newX, newY, duration.doubleValue(),tempX,tempY);
            var animation = animationQueue.makeAnimation(turtle, path, newAngle, duration);
            tempX = newX + TURTLE_SIZE/2;
            tempY = newY + TURTLE_SIZE/2;
            setupAnimation(turtleModel, newX, newY, newAngle, animation);
        });
    }

    private void setupAnimation(TurtleModel turtleModel, double newX, double newY, double newAngle, Animation animation) {
        var capturedPenDown = penDown.getValue();

        animation.currentTimeProperty().addListener((e, o, n) -> {
            if(n.toMillis() > duration.doubleValue()) return;
            if(capturedPenDown) {
                var temp = views.getChildren().get(views.getChildren().size()-1);
                if (temp.getClass().getSimpleName().equals("Path"))
                {
                    views.getChildren().remove(views.getChildren().size()-1);
                }
                if(newX!=turtle.getX() || newY!=turtle.getY())
                    views.getChildren().add(makePath(newX, newY, n.toMillis(),turtle.getX()+TURTLE_SIZE/2,turtle.getY()+TURTLE_SIZE/2)); }
        });
        animation.setOnFinished(e -> {
            views.getChildren().add(new Group());
            turtle.setTranslateX(0);
            turtle.setTranslateY(0);
            turtle.setX(newX);
            turtle.setY(newY);
            animationQueue.getPlaying().set(false);
            views.getChildren().add(new Group());
        });
    }

    private Path makePath(double newX, double newY, double o, double oldX, double oldY) {
        var path = new Path();
        if(o == duration.doubleValue())
            path.setFill(penColor);
        path.getElements().add(new MoveTo(oldX,oldY));
        double currentX = turtle.getX()+TURTLE_SIZE/2+o/duration.doubleValue() *(newX-turtle.getX());
        double currentY = turtle.getY()+TURTLE_SIZE/2+o/duration.doubleValue() *(newY-turtle.getY());
        path.getElements().add(new LineTo(currentX,currentY));
        path.setStroke(penColor);
        return path;
    }

    public Group views() { return views; }
    public void setTurtleImage(Image v) { turtleImg = v; turtle.setImage(v); }
    public void setPenColor(Color c) { penColor = c; }

    @Override
    public void clear() {
        views.getChildren().clear();
        views.getChildren().add(turtle);
    }
}
