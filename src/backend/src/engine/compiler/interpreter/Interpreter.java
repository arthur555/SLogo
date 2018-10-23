package engine.compiler.interpreter;

import engine.commands.Command;

import engine.compiler.slogoast.Expression;
import engine.errors.InterpretationException;
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
     * Read in the abstract syntax tree and evaluate the return value of the expression.
     * Note this is a family of methods because it uses the Visitor pattern to employ different interpret methods for different kinds (subclasses) of Expressions.
     *
     * @param astNode: SLogoAST representing the command tree.
     * @throws InterpretationException
     */
    void interpret(Expression astNode) throws InterpretationException;

    /**
     * This method evaluates the return value of the abstract syntax tree, not executing any of the commands.
     * Note this is a family of methods because it uses the Visitor pattern to employ different evaluate methods for different kinds (subclasses) of Expressions.
     *
     * @param astNode: SLogoAST representing the parsed command.
     * @return A double value returned by evaluating this command, however not executing any of these commands on the turtle.
     * @throws InterpretationException
     */
    double evaulate(Expression astNode) throws InterpretationException;
}
