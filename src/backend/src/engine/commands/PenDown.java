package engine.commands;

import model.TurtleModel;

public class PenDown implements Command<TurtleModel> {
    public PenDown(){}


    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.setPenDown(true);
    }
}
