package engine.commands;

import model.TurtleModel;
import model.TurtleModelImpl;

import java.util.ArrayList;
import java.util.List;

public class If implements Command<TurtleModelImpl> {
    private int expr;
    private List<Command<TurtleModelImpl>> commandListIf;
    public If(int expr, List<Command<TurtleModelImpl>> list){
        this.expr = expr;
        commandListIf = list;
    }
    @Override
    public double update(TurtleModelImpl turtleModel) {
        IfElse other = new IfElse(expr, commandListIf, new ArrayList<>());
        return other.update(turtleModel);
    }
}
