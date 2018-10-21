package engine.commands;

import model.TurtleModelImpl;

public class Cosine implements Command<TurtleModelImpl> {
    private double angle;

    public Cosine(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.cos(angle);
    }
}
