package engine.commands;

import model.TurtleModel;

public class SetPosition implements Command<TurtleModel> {

    private int x;
    private int y;

    public SetPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        double myX = turtleModel.getX();
        double myY = turtleModel.getY();
        turtleModel.setX(x);
        turtleModel.move(true);
        turtleModel.setY(y);
        turtleModel.move(false);
        return Math.sqrt((myX - x)*(myX-x) + (myY - y)*(myY - y));

    }
}
