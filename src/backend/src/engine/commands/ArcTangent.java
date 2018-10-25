package engine.commands;

import model.TurtleModel;

public class ArcTangent implements Command<TurtleModel> {
    private double degree;
    public ArcTangent(double d){
        degree = d;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.atan(degree);
    }
}
