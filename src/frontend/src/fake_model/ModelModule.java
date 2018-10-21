package fake_model;


import model.TurtleModelImpl;

public class ModelModule {
    private TurtleModelImpl turtleModel;

    public ModelModule() { turtleModel = new TurtleModelImpl(); }

    public TurtleModelImpl turtleModel() { return turtleModel; }
}
