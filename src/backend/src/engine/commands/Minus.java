package engine.commands;

import model.TurtleModelImpl;

public class Minus implements Command<TurtleModelImpl> {
    private double num;
    public Minus(double a){
        num = a;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return -1*num;
    }
}
