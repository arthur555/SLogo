package engine.commands;

import model.TurtleModelImpl;

public class PenUp implements Command<TurtleModelImpl> {
    public PenUp(){}

    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.setPenDown(false);
        return 0;
    }
}
