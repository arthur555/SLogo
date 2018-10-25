package controller;

import app.TabbedApp;
import engine.api.EngineAPI;
import fake_model.ModelModule;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import view.ViewModule;

import java.util.Collections;
import java.util.List;

public class ControllerModule {
    public static final List<String> LANGUAGES = Collections.unmodifiableList(
            List.of(
                    "Chinese",
                    "English",
                    "French",
                    "German",
                    "Italian",
                    "Portuguese",
                    "Russian",
                    "Spanish"
            )
    );

    private static final String DEFAULT_LANG = "English";

    private TurtleController turtleController;
    private EditorController editorController;
    private SidebarController sidebarController;

    public ControllerModule(
            TabbedApp app,
            ModelModule modelModule,
            ViewModule viewModule,
            EngineAPI engineApi
    ) {
        turtleController = new TurtleController(modelModule.turtleModel(), viewModule.turtleView());
        editorController = new EditorController(DEFAULT_LANG, viewModule.commandView(), viewModule.historyView(), engineApi);
        sidebarController = new SidebarController(DEFAULT_LANG, app, viewModule.sidebarView(), viewModule.turtleView());
        assemble();
        setupTopLevelHandler(viewModule.mainView().view());
    }

    private void assemble() { sidebarController.registerControllers(turtleController, editorController); }

    private void setupTopLevelHandler(Node topLevelNode) {
        topLevelNode.addEventFilter(KeyEvent.KEY_PRESSED, editorController::handleKeyPressed);
    }
}
