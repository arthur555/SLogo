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
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        ModelModule modelModule = new ModelModule();
        ViewModule viewModule = new ViewModule(modelModule);

        viewModule.show(primaryStage);

        ControllerModule controllerModule = new ControllerModule(modelModule, viewModule);
    }

    public static void main(String[] args) { launch(args); }
}
