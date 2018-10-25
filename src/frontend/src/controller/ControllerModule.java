package controller;

import app.TabbedApp;
import engine.api.EngineAPI;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import model.ModelModule;
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

    private CanvasController canvasController;
    private EditorController editorController;
    private SidebarController sidebarController;

    public ControllerModule(
            TabbedApp app,
            ModelModule modelModule,
            EngineAPI engineApi,
            ViewModule viewModule
    ) {
        editorController = new EditorController(DEFAULT_LANG, viewModule.commandView(), viewModule.historyView(), engineApi);
        sidebarController = new SidebarController(DEFAULT_LANG, app, viewModule.sidebarView());
        canvasController = new CanvasController(modelModule.turtleManager(), viewModule.canvasView());
        assemble();
        setupTopLevelHandler(viewModule.mainView().view());
    }

    private void assemble() { sidebarController.registerControllers(editorController, canvasController); }

    private void setupTopLevelHandler(Node topLevelNode) {
        topLevelNode.addEventFilter(KeyEvent.KEY_PRESSED, editorController::handleKeyPressed);
    }
}
