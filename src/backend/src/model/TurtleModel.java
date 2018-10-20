package model;

import engine.commands.TurtleCommand;

import java.util.List;

/**
 * This interface handles the behavior of the underlying turtle model.
 *
 * @author Haotian Wang
 */
public interface TurtleModel {

    /**
     * This is a default method to all classes extending TurtleModel such that all the turtle models take in a list of TurtleCommand and act on them.
     *
     * @param commands: A list of TurtleCommand.
     */
    default void actOnListOfCommands(List<TurtleCommand<TurtleModel>> commands) {
        commands.forEach(a -> a.update(this));
    }

    /**
     * This is a default method to all classes extending TurtleModel such that all turtle models take in a TurtleCommand and acts on it.
     *
     * @param command: A single TurtleCommand
     */
    default void actOnCommand(TurtleCommand<TurtleModel> command) {
        command.update(this);
    }
}
