package engine.commands;

import model.TurtleModelImpl;

public class LessThan implements Command<TurtleModelImpl> {
    private double num1;
    private double num2;
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return num1 < num2 ?  1:0;
    }
}
