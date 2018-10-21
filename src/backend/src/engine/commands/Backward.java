package engine.commands;

import model.TurtleModel;

public class Backward implements Command<TurtleModel>{
    private Forward fd;
    public Backward(int step) {
        fd = new Forward(-1*step);
    }

    /**
     * Update the states of the turtle model
     *
     * @param turtleModel
     */
    @Override
    public double update(TurtleModel turtleModel) {
        return fd.update(turtleModel);
    }
}
