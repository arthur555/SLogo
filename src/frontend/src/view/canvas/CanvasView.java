package view.canvas;


import javafx.scene.layout.Pane;
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
