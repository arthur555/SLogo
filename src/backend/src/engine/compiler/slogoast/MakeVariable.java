package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class handles the assignment grammar in the AST.
 *
 * @author Haotian Wang
 */
public class MakeVariable implements Expression {
    private Token myToken;
    private Variable myVar;
    private Expression myExpr;

    public MakeVariable(Token token, Variable var, Expression a) {
        myToken = token;
        myVar = var;
        myExpr = a;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", myToken.getString(), myVar.toString(), myExpr.toString());
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
