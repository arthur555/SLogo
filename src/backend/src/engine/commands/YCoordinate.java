package engine.commands;

import model.TurtleModel;

public class YCoordinate implements Command<TurtleModel> {
    public YCoordinate(){}
    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.getY();
    }
}
