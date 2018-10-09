import controller.ControllerModule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ModelModule;
import view.ViewModule;

public class SLogoApp extends Application {
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = MILLISECOND_DELAY / 1000.;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        var modelModule = new ModelModule();
        var viewModule = new ViewModule(modelModule);
        var controllerModule = new ControllerModule(modelModule, viewModule);

        startTick();
        viewModule.show(primaryStage);
    }

    private void tick(double elapsedTime) {
        // propagate tick to whichever module -> classes necessary
        // for this application, it would probably be view,
        // since it will consume TurtleModel's intermediate positions every x milliseconds
    }

    private void startTick() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> tick(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public static void main(String[] args) { launch(args); }
}
