package engine.commands;

import model.TurtleModel;

public class IsPenDown implements Command<TurtleModel> {
    public IsPenDown(){}
    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.isPenDown().getValue() ? 1 : 0;
    }
}
