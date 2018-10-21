package engine.commands;

import model.TurtleModel;

public class Minus implements Command<TurtleModel> {
    private double num;
    public Minus(double a){
        num = a;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return -1*num;
    }
}
