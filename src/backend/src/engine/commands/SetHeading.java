package engine.commands;

import model.TurtleModel;

public class SetHeading implements  Command<TurtleModel> {
    private double head;

    public SetHeading(double head){
        this.head = head;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        double angleMoved = head - turtleModel.getAngle();
        turtleModel.move(true);
        turtleModel.setAngle(head);
        turtleModel.move(false);
        return angleMoved%360;
    }
}
