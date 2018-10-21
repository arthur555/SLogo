package engine.commands;

import model.TurtleModelImpl;

public class YCoordinate implements Command<TurtleModelImpl> {
    public YCoordinate(){}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return turtleModel.getY();
    }
}
