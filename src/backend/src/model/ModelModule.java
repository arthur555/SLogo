package model;

import model.impl.TurtleManagerImpl;

public class ModelModule {
    public static final int INITIAL_TURTLE_ID = 1;

    private TurtleManager turtleManager;
    public ModelModule() {
        turtleManager = new TurtleManagerImpl();
        try { turtleManager.addTurtle(1); } catch (Exception ignored) { }
    }
    public TurtleManager turtleManager() { return turtleManager; }
}
