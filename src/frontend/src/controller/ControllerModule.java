package controller;

import model.ModelModule;
import view.ViewModule;

public class ControllerModule {
    private TurtleController turtleController;
    private EditorController editorController;
    private SidebarController sidebarController;

    public ControllerModule(ModelModule modelModule, ViewModule viewModule) {
        turtleController = new TurtleController(modelModule.turtleModel(), viewModule.turtleView());
        editorController = new EditorController(viewModule.commandView(), viewModule.historyView());
        sidebarController = new SidebarController(viewModule.sidebarView(), viewModule.turtleView());
        assemble();
    }

    private void assemble() {
        sidebarController.registerControllers(turtleController, editorController);
    }
}
