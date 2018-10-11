package slogo.controller;

import slogo.controller.canvas.CanvasController;
import slogo.controller.editor.EditorController;
import model.ModelModule;
import view.ViewModule;

public class ControllerModule {
    private CanvasController canvasController;
    private EditorController editorController;

    public ControllerModule(ModelModule modelModule, ViewModule viewModule) {
        canvasController = new CanvasController(modelModule.turtleModel(), viewModule.canvasView());
        editorController = new EditorController(viewModule.editorView());
    }
}
