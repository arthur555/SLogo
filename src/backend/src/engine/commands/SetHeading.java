package engine.commands;

import model.TurtleModel;

public class SetHeading implements  Command<TurtleModel> {
    private double head;

    public SetHeading(double head){
        this.head = head;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        return turtleModel.setAngle(head);
    }
}
