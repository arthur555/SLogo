package engine.commands;

import model.TurtleModel;

public class Tangent implements Command<TurtleModel> {
    private double angle;

    public Tangent(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.tan(angle);
    }
}
