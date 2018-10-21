package engine.commands;

import model.TurtleModel;

public class PenUp implements Command<TurtleModel> {
    public PenUp(){}

    @Override
    public double update(TurtleModel turtleModel) {
        turtleModel.setPenDown(false);
        return 0;
    }
}
