package engine.commands;

import model.TurtleModel;

public class Home implements Command<TurtleModel> {
    public Home(){}
    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.moveTo(0, 0, true);
    }
}
