package engine.commands;

import model.TurtleModelImpl;

public class HideTurtle implements Command<TurtleModelImpl> {
    public HideTurtle(){}

    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.setVisible(false);
        return 0;
    }
}
