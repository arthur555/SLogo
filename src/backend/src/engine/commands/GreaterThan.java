package engine.commands;

import model.TurtleModel;

public class GreaterThan implements Command<TurtleModel> {
    private double num1;
    private double num2;

    public GreaterThan(double a, double b){
        num1 = a;
        num2 = b;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return num1 > num2 ? 1:0;
    }
}
