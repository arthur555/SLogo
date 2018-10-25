package engine.commands;

import model.TurtleModel;

public class Sine implements Command<TurtleModel> {
    private double angle;

    public Sine(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.sin(angle);
    }
}
