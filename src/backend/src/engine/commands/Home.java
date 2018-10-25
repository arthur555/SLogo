package engine.commands;

import model.TurtleModel;

public class Home implements Command<TurtleModel> {
    public Home(){}
    @Override
    public double update(TurtleModel turtleModel) {
        var prevPen = turtleModel.isPenDown();
        turtleModel.setPenDown(false);
        double myX = turtleModel.getX();
        double myY = turtleModel.getY();
        turtleModel.moveTo(0, 0);
        turtleModel.setPenDown(prevPen);
        return Math.sqrt(myX*myX + myY*myY);
    }
}
