package controller;

import model.ModelModule;
import view.ViewModule;

import java.util.Collections;
import java.util.List;

public class ControllerModule {
    public static final List<String> LANGUAGES = Collections.unmodifiableList(
            List.of(
                    "Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"
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
    }

    private void assemble() {
        sidebarController.registerControllers(turtleController, editorController);
    }
}
