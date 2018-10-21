package engine.commands;

import model.TurtleModelImpl;

public class SetHeading implements  Command<TurtleModelImpl> {
    private double head;

    public SetHeading(double head){
        this.head = head;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        double angleMoved = head - turtleModel.getAngle();
        turtleModel.move(true);
        turtleModel.setAngle(head);
        turtleModel.move(false);
        return angleMoved%360;
    }
}
