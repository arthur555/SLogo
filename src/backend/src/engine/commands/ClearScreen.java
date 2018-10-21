package engine.commands;

import model.TurtleModel;

public class ClearScreen implements  Command<TurtleModel>  {
    public ClearScreen(){}
    @Override
    public double update(TurtleModel turtleModel) {
        Home hm = new Home();
        turtleModel.clean();
        turtleModel.setVisible(true);
        return hm.update(turtleModel);
    }
}
