package engine.commands;

import model.TurtleModel;

public class Home implements Command<TurtleModel> {
    public Home(){}
    @Override
    public double update(TurtleModel turtleModel) {
        var prevPen = turtleModel.isPenDown().getValue();
        turtleModel.setPenDown(false);
        double myX = turtleModel.getX();
        double myY = turtleModel.getY();
        turtleModel.setX(0);
        turtleModel.move(true);
        turtleModel.setY(0);
        turtleModel.move(false);
        turtleModel.setPenDown(prevPen);
        return Math.sqrt(myX*myX + myY*myY);
    }
}
