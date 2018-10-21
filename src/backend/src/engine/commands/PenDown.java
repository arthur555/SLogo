package engine.commands;

import model.TurtleModelImpl;

public class PenDown implements Command<TurtleModelImpl> {
    public PenDown(){}


    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.setPenDown(true);
        return 1;
    }
}
