package engine.interpreter;

import engine.commands.Command;
import engine.parser.SLogoAST;

import java.util.List;

/**
 * This is a crude interpreter implementation.
 *
 * @author Haotian Wang
 */
public class CrudeInterpreter implements Interpreter{

    private StateMachine myVariables;

    /**
     * Read in the commands
     *
     * @param ast : SLogoAST representing the command tree.
     */
    @Override
    public void readCommands(SLogoAST ast) {

    }

    /**
     * This returns a list of actionable Command after the interpreter interprets the SLogoAST.
     *
     * @return A list of actionable Command.
     */
    @Override
    public List<Command<TurtleModel>> outputTurtleCommands() {
        return null;
    }

    /**
     * This clears the internal AST stored in the interpreter.
     */
    @Override
    public void clearCommands() {

    }
}
