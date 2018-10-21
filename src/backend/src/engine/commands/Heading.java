package engine.commands;

import model.TurtleModelImpl;

public class Heading implements Command<TurtleModelImpl> {
    public Heading(){}

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return turtleModel.getAngle();
    }
}
