package engine.commands;

import model.TurtleModelImpl;

public class Backward implements Command<TurtleModelImpl>{
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
    public void update(TurtleModelImpl turtleModel) {
        fd.update(turtleModel);
    }
}
