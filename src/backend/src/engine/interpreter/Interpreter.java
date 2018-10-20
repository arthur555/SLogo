package engine.interpreter;

import engine.parser.SLogoAST;

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

    void applyCommands();

    void clearCommands();
}
