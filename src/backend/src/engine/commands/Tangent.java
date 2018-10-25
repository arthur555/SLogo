package engine.commands;

import model.impl.TurtleModelImpl;

public class Tangent implements Command<TurtleModelImpl> {
    private double angle;

    public Tangent(double a){
        angle = a;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.tan(angle);
    }
}
