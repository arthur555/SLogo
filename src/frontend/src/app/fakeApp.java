package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.TurtleModel;
import model.TurtleModelImpl;
import view.TurtleView;

public class fakeApp extends Application {
    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = MILLISECOND_DELAY / 1000.;

    @Override
    public void start(Stage primaryStage) {
        TurtleModel turtle = new TurtleModelImpl();
        TurtleView view = new TurtleView(turtle);
        turtle.setX(100);
        turtle.move(true);
        //turtle.setY(200);

    }

    private void tick(double elapsedTime) { }

    private void startTick() {
    }

    public static void main(String[] args) { launch(args); }
}
