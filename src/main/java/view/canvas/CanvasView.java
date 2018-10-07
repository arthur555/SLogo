package view.canvas;

import javafx.scene.Group;
import javafx.scene.text.Text;
import model.turtle.TurtleModel;

public class CanvasView extends Group {
    public CanvasView(TurtleModel turtleModel) {
        getChildren().add(new Text("Canvas"));
    }
}
