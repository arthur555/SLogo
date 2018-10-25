package engine.commands;

import model.TurtleModel;
import java.util.List;

public class Repeat implements Command<TurtleModel> {
    private static final String repeatCountKey = ":repcount";


    private int numTimes;
    private List<Command<TurtleModel>> commandList;

    public Repeat(int numTimes, List<Command<TurtleModel>> commandList){
        this.numTimes = numTimes;
        this.commandList = commandList;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        double ret = 0;
        for (int i = 1; i <= numTimes; i++){
            turtleModel.memory().setInteger(repeatCountKey, i);
            for (Command<TurtleModel> command: commandList){
                ret = command.update(turtleModel);
            }
        }
        return ret;
    }
}
