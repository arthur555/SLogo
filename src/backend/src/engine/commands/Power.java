package engine.commands;

import model.TurtleModelImpl;

public class Power implements Command<TurtleModelImpl> {
    private double base;
    private double exp;
    public Power (double base, double exp){
        this.base = base;
        this.exp = exp;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.pow(base, exp);
    }
}
