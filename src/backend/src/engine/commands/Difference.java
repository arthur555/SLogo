package engine.commands;

import model.TurtleModel;

public class Difference implements Command<TurtleModel> {
    private double num1;
    private double num2;

    public Difference(double one, double two){
        num1 = one;
        num2 = two;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return num1 - num2;
    }
}
