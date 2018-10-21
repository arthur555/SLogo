package fake_model;


import model.TurtleModel;
import model.TurtleModelImpl;

public class ModelModule {
    private TurtleModel turtleModel;

    public ModelModule() { turtleModel = new TurtleModelImpl(); }

    public TurtleModel turtleModel() { return turtleModel; }
}
