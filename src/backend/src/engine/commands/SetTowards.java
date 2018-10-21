package engine.commands;

import model.TurtleModel;

public class SetTowards implements  Command<TurtleModel> {

    private int x;
    private int y;

    public SetTowards(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        double currentX = turtleModel.getX();
        double currentY = turtleModel.getY();
        double newAngle = Math.atan2(y - currentY, x - currentX);
        SetHeading head = new SetHeading(newAngle);
        return head.update(turtleModel);
    }
}
