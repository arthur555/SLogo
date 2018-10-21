package engine.commands;

import model.TurtleModelImpl;

public class IsShowing implements Command<TurtleModelImpl> {
    public IsShowing(){}

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return turtleModel.isVisible().getValue() ? 1 : 0;
    }
}
