package engine.commands;

import model.impl.TurtleModelImpl;
import java.util.List;

public class Repeat implements Command<TurtleModelImpl> {
    private static final String repeatCountKey = ":repcount";


    private int numTimes;
    private List<Command<TurtleModelImpl>> commandList;

    public Repeat(int numTimes, List<Command<TurtleModelImpl>> commandList){
        this.numTimes = numTimes;
        this.commandList = commandList;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        double ret = 0;
        for (int i = 1; i <= numTimes; i++){
            turtleModel.getMemory().setInteger(repeatCountKey, i);
            for (Command command: commandList){
                ret = command.update(turtleModel);
            }
        }
        return ret;
    }
}
