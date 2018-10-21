package engine.commands;

import model.TurtleModelImpl;

public class ShowTurtle implements Command<TurtleModelImpl> {

    public ShowTurtle(){}

    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.setVisible(true);
        return 1;
    }
}
