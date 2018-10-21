package engine.commands;

import model.TurtleModelImpl;

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
