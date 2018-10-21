package engine.commands;

import model.TurtleModelImpl;

public class ClearScreen implements  Command<TurtleModelImpl>  {
    public ClearScreen(){}
    @Override
    public double update(TurtleModelImpl turtleModel) {
        Home hm = new Home();
        turtleModel.clean();
        turtleModel.setVisible(true);
        return hm.update(turtleModel);
    }
}
