package engine.compiler.slogoast;

import engine.compiler.Token;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the expression, a list of variables. For example, [ :x :y] is a list of variables.
 *
 * @author Haotian Wang
 */
public class VariableList extends Expression {
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
}
