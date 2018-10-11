package slogo.controller.canvas;

import model.turtle.TurtleModel;
import view.canvas.CanvasView;

public class CanvasController {
    private CanvasView canvasView;
    private TurtleModel turtleModel;

    public CanvasController(TurtleModel turtleModel, CanvasView canvasView) {
        this.turtleModel = turtleModel;
        this.canvasView = canvasView;
        this.canvasView.registerController(this);
    }
}
