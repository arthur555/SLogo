package engine.commands;

import model.TurtleModel;

public class Pi implements Command<TurtleModel> {
    public Pi(){}
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.PI;
    }
}
