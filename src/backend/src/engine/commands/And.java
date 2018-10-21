package engine.commands;

import model.TurtleModelImpl;

public class And implements Command<TurtleModelImpl> {
    int ret;
    public And(double one, double two){
        ret = (one != 0 && two != 0) ? 1:0;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return ret;
    }
}
