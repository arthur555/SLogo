package engine.commands;

import model.TurtleModel;

public class XCoordinate implements Command<TurtleModel> {
    public XCoordinate (){}
    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.getX();
    }
}
