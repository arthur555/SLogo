package view.canvas;

import controller.canvas.CanvasController;
import javafx.scene.Group;
import javafx.scene.text.Text;
import model.turtle.TurtleModel;

public class CanvasView extends Group {
    private CanvasController canvasController;

    public CanvasView(TurtleModel turtleModel) {
        getChildren().add(new Text("Canvas"));
    }

    public void registerController(CanvasController controller) {
        canvasController = controller;
    }
}
