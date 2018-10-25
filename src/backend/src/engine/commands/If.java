package engine.commands;

import model.TurtleModel;

import java.util.ArrayList;
import java.util.List;

public class If implements Command<TurtleModel> {
    private int expr;
    private List<Command<TurtleModel>> commandListIf;
    public If(int expr, List<Command<TurtleModel>> list){
        this.expr = expr;
        commandListIf = list;
    }
    @Override
    public double update(TurtleModel turtleModel) {
        IfElse other = new IfElse(expr, commandListIf, new ArrayList<>());
        return other.update(turtleModel);
    }
}
