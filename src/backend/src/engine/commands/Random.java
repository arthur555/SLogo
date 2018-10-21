package engine.commands;

import model.TurtleModel;

public class Random implements Command<TurtleModel> {
    private double limit;
    public Random(double limit){
        this.limit = limit;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.random()*limit;
    }
}
