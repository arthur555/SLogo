package engine.commands;

import model.TurtleModel;

public class NaturalLog implements Command<TurtleModel> {
    private double num;
    public NaturalLog(double a){
        num = a;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        return Math.log(num);
    }
}
