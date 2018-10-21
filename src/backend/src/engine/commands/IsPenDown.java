package engine.commands;

import model.TurtleModelImpl;

public class IsPenDown implements Command<TurtleModelImpl> {
    public IsPenDown(){}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return turtleModel.isPenDown().getValue() ? 1 : 0;
    }
}
