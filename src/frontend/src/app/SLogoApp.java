package app;

import controller.ControllerModule;

import engine.api.ASTEngineAPI;
import engine.api.EngineAPI;
import engine.api.FakeEngineAPI;
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
        EngineAPI engineApi = new FakeEngineAPI(modelModule.turtleModel());
        ViewModule viewModule = new ViewModule(modelModule);

        viewModule.show(primaryStage);

        ControllerModule controllerModule = new ControllerModule(modelModule, viewModule, engineApi);
    }

    public static void main(String[] args) { launch(args); }
}
