package engine.commands;

import model.TurtleModel;

public class HideTurtle implements Command<TurtleModel> {
    public HideTurtle(){}

    @Override
    public double update(TurtleModel turtleModel) {
        turtleModel.setVisible(false);
        return 0;
    }
}
