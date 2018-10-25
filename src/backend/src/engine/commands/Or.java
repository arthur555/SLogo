package engine.commands;

import model.impl.TurtleModelImpl;

public class Or implements Command<TurtleModelImpl> {
    private double num1;
    private double num2;
    public Or(double a, double b){
        num1 = a;
        num2 = b;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return (num1 != 0 || num2 != 0) ? 1:0;
    }
}
