package model;

import engine.commands.TurtleCommand;

public interface TurtleModel {
    void act(TurtleCommand<TurtleModel> command);
}
