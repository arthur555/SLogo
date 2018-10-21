package engine.commands;

import model.TurtleModelImpl;

public class NaturalLog implements Command<TurtleModelImpl> {
    private double num;
    public NaturalLog(double a){
        num = a;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return Math.log(num);
    }
}
