package engine.commands;

import model.impl.TurtleModelImpl;

public class MakeVariable implements Command <TurtleModelImpl> {
    private String key;
    private double value;

    public MakeVariable(String key, double value){
        this.key = key;
        this.value = value;
    }

    @Override
    public double update(TurtleModelImpl turtleModel) {
        turtleModel.getMemory().setDouble(key, value);
        return value;
    }
}
