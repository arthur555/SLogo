package engine.commands;

import model.TurtleModel;

public class Heading implements Command<TurtleModel> {
    public Heading(){}

    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.getAngle();
    }
}
