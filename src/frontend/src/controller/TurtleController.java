package controller;

import fake_model.TurtleModel;
import view.TurtleView;

public class TurtleController {
    private TurtleView turtleView;
    private TurtleModel turtleModel;

    public TurtleController(TurtleModel turtleModel, TurtleView turtleView) {
        this.turtleModel = turtleModel;
        this.turtleView = turtleView;

    }
}

