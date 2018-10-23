package engine.compiler.slogoast;

import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This is an abstract class that all AST nodes shall extend from.
 *
 * @author Haotian Wang
 */
public interface Expression {
    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    String toString();

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    void execute(Interpreter interpreter) throws InterpretationException;
}
