package engine.commands;

import model.TurtleModel;

public class Not implements Command<TurtleModel>  {
    double ret;
    public Not (double val){ret = val;}
    @Override
    public double update(TurtleModel turtleModel) {
        return (ret == 0) ? 1:0;
    }
}
