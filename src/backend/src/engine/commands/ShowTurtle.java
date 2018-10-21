package engine.commands;

import model.TurtleModel;

public class ShowTurtle implements Command<TurtleModel> {

    public ShowTurtle(){}

    @Override
    public double update(TurtleModel turtleModel) {
        turtleModel.setVisible(true);
        return 1;
    }
}
