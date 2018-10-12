package model;

public class ModelModule {
    private TurtleModel turtleModel;

    public ModelModule() { turtleModel = new TurtleModelImpl(); }

    public TurtleModel turtleModel() { return turtleModel; }
}
