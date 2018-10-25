package engine.commands;

import model.TurtleModel;

public class Power implements Command<TurtleModel> {
    private double base;
    private double exp;
    public Power (double base, double exp){
        this.base = base;
        this.exp = exp;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return Math.pow(base, exp);
    }
}
