package engine.commands;

import model.TurtleModelImpl;

public class Right implements Command<TurtleModelImpl> {
    private Left left;

    public Right (double degree){
        left = new Left(-1*degree);
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        return left.update(turtleModel);
    }
}
