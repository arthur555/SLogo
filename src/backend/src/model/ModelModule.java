package model;

import model.impl.TurtleManagerImpl;

public class ModelModule {
    private TurtleManager turtleManager;
    public ModelModule() { turtleManager = new TurtleManagerImpl(); }
    public TurtleManager turtleManager() { return turtleManager; }
}
