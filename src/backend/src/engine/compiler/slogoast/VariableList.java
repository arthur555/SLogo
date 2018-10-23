package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the expression, a list of variables. For example, [ :x :y] is a list of variables.
 *
 * @author Haotian Wang
 */
public class VariableList implements Expression {
    private Token listStart;
    private List<Variable> variableList;
    private Token listEnd;

    public VariableList(Token start, List<Variable> list, Token end) {
        listStart = start;
        variableList = list;
        listEnd = end;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return Arrays.toString(variableList.toArray(new Variable[variableList.size()]));
    }

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.interpret(this);
    }
}
