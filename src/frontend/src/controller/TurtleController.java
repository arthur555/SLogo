package controller;

import engine.commands.Command;
import model.TurtleModel;
import view.TurtleView;

import java.util.List;

public class TurtleController {
    private TurtleView turtleView;
    private TurtleModel turtleModel;

    public TurtleController(TurtleModel turtleModel, TurtleView turtleView) {
        this.turtleModel = turtleModel;
        this.turtleView = turtleView;

    }

    void actOnListOfCommands(List<Command<model.TurtleModel>> commands) {
        commands.forEach(a -> a.update(turtleModel));
    }

    void actOnCommand(Command<TurtleModel> command) {
        command.update(turtleModel);
    }
}
