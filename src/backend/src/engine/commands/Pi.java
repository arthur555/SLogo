package engine.commands;

import model.TurtleModelImpl;

public class Pi implements Command<TurtleModelImpl> {
    public Pi(){}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.PI;
    }
}
