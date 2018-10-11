package model;

import model.turtle.TurtleModel;

public class ModelModule {
    private TurtleModel turtleModel;

    public ModelModule() {
        turtleModel = new TurtleModel();
    }

    public TurtleModel turtleModel() { return turtleModel; }
}
