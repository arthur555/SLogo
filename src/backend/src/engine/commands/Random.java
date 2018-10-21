package engine.commands;

import model.TurtleModelImpl;

public class Random implements Command<TurtleModelImpl> {
    private double limit;
    public Random(double limit){
        this.limit = limit;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.random()*limit;
    }
}
