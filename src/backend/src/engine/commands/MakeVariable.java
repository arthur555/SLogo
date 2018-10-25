package engine.commands;

import model.TurtleModel;

public class MakeVariable implements Command <TurtleModel> {
    private String key;
    private double value;

    public MakeVariable(String key, double value){
        this.key = key;
        this.value = value;
    }

    @Override
    public double update(TurtleModel turtleModel) {
        turtleModel.memory().setDouble(key, value);
        return value;
    }
}
