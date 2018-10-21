package engine.commands;

import model.TurtleModel;

public class Right implements Command<TurtleModel> {
    private Left left;

    public Right (double degree){
        left = new Left(-1*degree);
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return left.update(turtleModel);
    }
}
