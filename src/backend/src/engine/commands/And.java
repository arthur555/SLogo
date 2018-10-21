package engine.commands;

import model.TurtleModel;

public class And implements Command<TurtleModel> {
    private double one;
    private double two;
    public And(double one, double two){
        this.one = one;
        this.two = two;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return (one != 0 && two != 0) ? 1:0;
    }
}
