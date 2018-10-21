package engine.commands;

import model.TurtleModelImpl;

public class Difference implements Command<TurtleModelImpl> {
    private double num1;
    private double num2;

    public Difference(double one, double two){
        num1 = one;
        num2 = two;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return num1 - num2;
    }
}
