package engine.commands;

import model.TurtleModelImpl;

public class Product implements Command<TurtleModelImpl> {
    private double num1;
    private double num2;
    public Product(double a, double b){
        num1 = a;
        num2 = b;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return num1*num2;
    }
}
