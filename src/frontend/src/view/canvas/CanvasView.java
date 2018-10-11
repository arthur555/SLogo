package view.canvas;

import main.java.controller.canvas.CanvasController;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleModel;

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
