package engine.commands;

import model.TurtleModel;

public class LessThan implements Command<TurtleModel> {
    private double num1;
    private double num2;
    @Override
    public double update(TurtleModel turtleModel) {
        return num1 < num2 ?  1:0;
    }
}
