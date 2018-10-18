package app;

import controller.ControllerModule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;


import fake_model.TurtleModel;
import view.TurtleView;

public class fakeApp extends Application {
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = MILLISECOND_DELAY / 1000.;

    @Override
    public void start(Stage primaryStage) {
        TurtleModel turtle = new TurtleModel();
        TurtleView view = new TurtleView(turtle);
        turtle.setX(100);
        //turtle.setY(200);

    }

    private void tick(double elapsedTime) { }

    private void startTick() {
    }

    public static void main(String[] args) { launch(args); }
}
