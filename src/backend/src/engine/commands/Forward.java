package engine.commands;

import model.TurtleModel;

public class Forward implements Command<TurtleModel> {

    private int stepLength;
    public Forward(int step) {
        stepLength = step;
    }

    /**
     * Update the states of the turtle model
     *
     * @param turtleModel
     */
    @Override
    public double update(TurtleModel turtleModel) {
        double head = turtleModel.getAngle();
        double stepX = stepLength*Math.cos(Math.toRadians(head));
        double stepY = stepLength*Math.sin(Math.toRadians(head));
        return turtleModel.moveTo(turtleModel.getX()+stepX, turtleModel.getY()+stepY);
    }
}
