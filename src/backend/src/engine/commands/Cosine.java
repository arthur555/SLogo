package engine.commands;

import model.TurtleModel;

public class Cosine implements Command<TurtleModel> {
    private double angle;

    public Cosine(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.cos(angle);
    }
}
