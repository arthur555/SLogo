package engine.commands;

import model.TurtleModelImpl;

public class ArcTangent implements Command<TurtleModelImpl> {
    private double degree;
    public ArcTangent(double d){
        degree = d;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.atan(degree);
    }
}
