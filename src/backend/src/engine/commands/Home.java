package engine.commands;

import model.TurtleModelImpl;

public class Home implements Command<TurtleModelImpl> {
    public Home(){}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        double myX = turtleModel.getX();
        double myY = turtleModel.getY();
        turtleModel.setX(0);
        turtleModel.move(true);
        turtleModel.setY(0);
        turtleModel.move(false);
        return Math.sqrt(myX*myX + myY*myY);
    }
}
