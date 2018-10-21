package controller;

import fake_model.ModelModule;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

    public ControllerModule(ModelModule modelModule, ViewModule viewModule) {
        turtleController = new TurtleController(modelModule.turtleModel(), viewModule.turtleView());
        editorController = new EditorController(DEFAULT_LANG, viewModule.commandView(), viewModule.historyView());
        sidebarController = new SidebarController(DEFAULT_LANG, viewModule.sidebarView(), viewModule.turtleView());
        assemble();
        setupTopLevelHandler(viewModule.scene());
    }

    private void assemble() { sidebarController.registerControllers(turtleController, editorController); }

    private void setupTopLevelHandler(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, editorController::handleKeyPressed);
    }
}
