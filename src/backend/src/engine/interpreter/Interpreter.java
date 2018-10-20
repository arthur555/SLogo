package engine.interpreter;

import engine.commands.TurtleCommand;
import engine.parser.SLogoAST;
import model.TurtleModel;

import java.util.List;

/**
 * Take in abstract syntax tree from Parser.
 * Generate a list of TurtleAction from the abstract syntax tree.
 * Or alternatively, instead of creating a TurtleAction object, output the desired coordinates and direction for the turtle after the end of each tick cycle.
 *
 * @author Haotian Wang
 */
public interface Interpreter {
    /**
     * Read in the commands
     *
     * @param ast: SLogoAST representing the command tree.
     */
    void readCommands(SLogoAST ast);

    /**
     * This returns a list of actionable TurtleCommand after the interpreter interprets the SLogoAST.
     *
     * @return A list of actionable TurtleCommand.
     */
    List<TurtleCommand<TurtleModel>> outputTurtleCommands();

    /**
     * This clears the internal AST stored in the interpreter.
     */
    void clearCommands();
}
