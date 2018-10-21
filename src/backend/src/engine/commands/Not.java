package engine.commands;

import model.TurtleModelImpl;

public class Not implements Command<TurtleModelImpl>  {
    double ret;
    public Not (double val){ret = val;}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        return (ret == 0) ? 1:0;
    }
}
