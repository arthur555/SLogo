package fake_model;

public class ModelModule {
    private TurtleModel turtleModel;

    public ModelModule() { turtleModel = new TurtleModel(); }

    public TurtleModel turtleModel() { return turtleModel; }
}
