package engine.commands;

import model.TurtleModel;
import java.util.List;

public class IfElse implements Command<TurtleModel> {
    private int expr;
    private List<Command<TurtleModel>> commandListIf;
    private List<Command<TurtleModel>> commandListElse;

    public IfElse(int expr, List<Command<TurtleModel>> ifList, List<Command<TurtleModel>> elseList){
        this.expr = expr;
        commandListIf = ifList;
        commandListElse = elseList;
    }


    @Override
    public double update(TurtleModel turtleModel) {
        double ret = 0;
        var commandList = commandListElse;
        if (expr != 0){
            commandList = commandListIf;
        }
        for (Command<TurtleModel> command: commandList){
            ret = command.update(turtleModel);
        }
        return ret;
    }
}
