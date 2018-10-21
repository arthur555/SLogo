package engine.commands;

import model.TurtleModel;

public class Product implements Command<TurtleModel> {
    private double num1;
    private double num2;
    public Product(double a, double b){
        num1 = a;
        num2 = b;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return num1*num2;
    }
}
