package app;

import controller.ControllerModule;

import fake_model.ModelModule;
import view.ViewModule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SLogoApp extends Application {
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = MILLISECOND_DELAY / 1000.;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        ModelModule modelModule = new ModelModule();
        ViewModule viewModule = new ViewModule(modelModule);
        ControllerModule controllerModule = new ControllerModule(modelModule, viewModule);

        startTick();
        viewModule.show(primaryStage);
        primaryStage.show();
    }

    private void tick(double elapsedTime) { }

    private void startTick() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> tick(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public static void main(String[] args) { launch(args); }
}
