package engine.commands;

import model.TurtleModelImpl;

public class Right implements Command<TurtleModelImpl> {
    private Left left;

    public Right (double degree){
        left = new Left(-1*degree);
    }

    @Override
    public void update(TurtleModelImpl turtleModel) {
        left.update(turtleModel);
    }
}
