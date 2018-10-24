package app;

import controller.ControllerModule;
import engine.api.ASTEngineAPI;
import engine.api.EngineAPI;
import fake_model.ModelModule;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewModule;

public class SLogoApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        ModelModule modelModule = new ModelModule();
        EngineAPI engineApi = new ASTEngineAPI(modelModule.turtleModel());
        ViewModule viewModule = new ViewModule(modelModule, engineApi);

        viewModule.show(primaryStage);

        ControllerModule controllerModule = new ControllerModule(modelModule, viewModule, engineApi);
    }

    public static void main(String[] args) { launch(args); }
}
