package controller;

import engine.commands.Command;
import fake_model.TurtleModel;
import model.TurtleModelImpl;
import view.TurtleView;

import java.util.List;

public class TurtleController {
    private TurtleView turtleView;
    private TurtleModelImpl turtleModel;

    public TurtleController(TurtleModelImpl turtleModel, TurtleView turtleView) {
        this.turtleModel = turtleModel;
        this.turtleView = turtleView;

    }

    void actOnListOfCommands(List<Command<model.TurtleModelImpl>> commands) {
        commands.forEach(a -> a.update(turtleModel));
    }

    void actOnCommand(Command<TurtleModelImpl> command) {
        command.update(turtleModel);
    }
}
