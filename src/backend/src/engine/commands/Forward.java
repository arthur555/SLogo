package engine.commands;
import model.TurtleModelImpl;
public class Forward implements Command<TurtleModelImpl> {

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
    public double update(TurtleModelImpl turtleModel) {
        double head = turtleModel.getAngle();
        double stepX = stepLength*Math.cos(head);
        double stepY = stepLength*Math.sin(head);
        turtleModel.setX(turtleModel.getX()+stepX);
        turtleModel.move(true);
        turtleModel.setY(turtleModel.getY()+stepY);
        turtleModel.move(false);
        return stepLength;
    }
}
