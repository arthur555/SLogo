package engine.commands;

import model.impl.TurtleModelImpl;

public class Sine implements Command<TurtleModelImpl> {
    private double angle;

    public Sine(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.sin(angle);
    }
}
