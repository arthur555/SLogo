package controller;

import model.ModelModule;
import view.ViewModule;

public class ControllerModule {
    private TurtleController canvasController;
    private EditorController editorController;

    public ControllerModule(ModelModule modelModule, ViewModule viewModule) {
        canvasController = new TurtleController(modelModule.turtleModel(), viewModule.turtleView());
        editorController = new EditorController(viewModule.editorView());
    }
}
