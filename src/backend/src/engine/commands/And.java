package engine.commands;

import model.TurtleModelImpl;

public class And implements Command<TurtleModelImpl> {
    private double one;
    private double two;
    public And(double one, double two){
        this.one = one;
        this.two = two;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return (one != 0 && two != 0) ? 1:0;
    }
}
