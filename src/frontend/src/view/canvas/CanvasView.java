package view.canvas;


import java.awt.*;

public class CanvasView extends Pane {
    private CanvasController canvasController;

    public CanvasView(TurtleModel turtleModel) {
        getStyleClass().add("canvas");
        getChildren().add(new Rectangle(200, 200, 200, 200));
    }

    public void registerController(CanvasController controller) {
        canvasController = controller;
    }
}
