package engine.commands;

import model.impl.TurtleModelImpl;

import java.util.List;

public class IfElse implements Command<TurtleModelImpl>{
    private int expr;
    private List<Command<TurtleModelImpl>> commandListIf;
    private List<Command<TurtleModelImpl>> commandListElse;

    public IfElse(int expr, List<Command<TurtleModelImpl>> ifList, List<Command<TurtleModelImpl>> elseList){
        this.expr = expr;
        commandListIf = ifList;
        commandListElse = elseList;
    }


    @Override
    public double update(TurtleModelImpl turtleModel) {
        double ret = 0;
        var commandList = commandListElse;
        if (expr != 0){
            commandList = commandListIf;
        }
        for (Command command: commandList){
            ret = command.update(turtleModel);
        }
        return ret;
    }
}
