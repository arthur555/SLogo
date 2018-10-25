package engine.commands;

import model.TurtleModel;

public class Or implements Command<TurtleModel> {
    private double num1;
    private double num2;
    public Or(double a, double b){
        num1 = a;
        num2 = b;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return (num1 != 0 || num2 != 0) ? 1:0;
    }
}
