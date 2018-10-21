package engine.commands;

import model.TurtleModelImpl;

import java.util.List;

public class For implements Command<TurtleModelImpl> {
    private String var;
    private double start;
    private double end;
    private double increment;
    private List<Command<TurtleModelImpl>> commands;
    public For (String var, double start, double end, double increment, List<Command<TurtleModelImpl>> commands){
        this.var = var;
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.commands = commands;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        double result = 0;
        for (double i = start; i < end; i += increment){
            turtleModel.getMemory().setDouble(var, i);
            for (Command command: commands){
                    result = command.update(turtleModel);
            }
        }
        return result;
    }
}
