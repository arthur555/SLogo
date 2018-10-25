package engine.commands;

import model.TurtleModel;

public class Left implements Command<TurtleModel> {
    private double degree;
    public Left(double degree){
        this.degree = degree;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        turtleModel.setAngle((degree + turtleModel.getAngle())%360);
        return degree;
    }
}
