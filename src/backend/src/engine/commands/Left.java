package engine.commands;

import model.TurtleModelImpl;

public class Left implements Command<TurtleModelImpl> {
    private double degree;
    public Left(double degree){
        this.degree = degree;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.move(true);
        turtleModel.setAngle((degree + turtleModel.getAngle())%360);
        turtleModel.move(false);
        return degree;
    }
}
