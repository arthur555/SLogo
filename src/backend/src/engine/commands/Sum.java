package engine.commands;

import model.TurtleModelImpl;

public class Sum implements Command<TurtleModelImpl> {
    private double num1;
    private double num2;
    public Sum(double a, double b){
        num1 = a;
        num2 = b;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return num1 + num2;
    }
}
