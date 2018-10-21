package engine.commands;

import model.TurtleModel;

public class IsShowing implements Command<TurtleModel> {
    public IsShowing(){}

    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.isVisible().getValue() ? 1 : 0;
    }
}
